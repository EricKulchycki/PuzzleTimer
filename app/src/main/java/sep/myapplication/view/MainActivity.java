package sep.myapplication.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.SystemClock;

import sep.myapplication.Application.Main;
import sep.myapplication.Application.Services;
import sep.myapplication.R;
import sep.myapplication.business.CalculateAverages;
import sep.myapplication.business.ScrambleGenerator;
import sep.myapplication.objects.Timer;
import sep.myapplication.persistence.DataAccessObject;

import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    TextView textview,averageText;
    Button start, stop;
    Handler handler;
    public int counter;
    Timer stopWatch = new Timer();
    private int selectedSession = 3;

    //Database where times are stored
    //Note, we should be able to turn this from a DAS into a DAO and have the program still work
    DataAccessObject timeList;//  = new DataAccessObject("timeList");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        copyDatabaseToDevice();
        Main.startUp();


        timeList = (DataAccessObject) Services.createDataAccess(Main.dbName);
        timeList.open(Main.getDBPathName());

        scrambleToDisplay();
        timer();
        averageToDisplay();

        }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        //Put times into a Long array
        long[] temp = new long[timeList.getSize()];
        ArrayList<Long> tempAL = timeList.getList();

        for(int i = 0; i < timeList.getSize(); i++) {
            temp[i] = tempAL.get(i);
        }

        outState.putLongArray("tempA", temp);

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        long[] temp = savedInstanceState.getLongArray("tempA");
        System.out.println(temp.length);

        for(int i = 0; i < temp.length; i++) {
            timeList.add(temp[i]);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        //getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem mitem = menu.findItem(R.id.puzzleType);
        Spinner spin = (Spinner) mitem.getActionView();
        setupSpinner(spin);

        return true;
    }

    public void setupSpinner(Spinner spin){
        String[] items={"2x2x2","3x3x3","4x4x4"};
        //wrap the items in the Adapter
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, items);
        //assign adapter to the Spinner
        spin.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action) {
            Intent intent = new Intent(this, TimeListActivity.class);
            startActivity(intent);
            return true;
        }
        if(id == R.id.puzzleType) {
            Spinner mySpinner=(Spinner) findViewById(R.id.puzzleType);
            String text = mySpinner.getSelectedItem().toString();

            System.out.println(text);

            if(text.equals("2x2x2")) {
                selectedSession = 2;
            } else if(text.equals("3x3x3")) {
                selectedSession = 3;
            } else if(text.equals("4x4x4")) {
                selectedSession = 4;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Main.shutDown();
    }

    //Displays the scramble on the screen
    public void scrambleToDisplay() {
        ScrambleGenerator scrambleGen = new ScrambleGenerator();

        String scramble = "";

        if(selectedSession == 3) {
            scramble = scrambleGen.genScramble(25);
        } else if(selectedSession == 2) {
            scramble = scrambleGen.genScramble(9);
        }


        TextView scrambleElement;

        scrambleElement = (TextView) findViewById(R.id.scramble);
        scrambleElement.setText(R.string.scramble_text);
        scrambleElement.setText(scramble);
        scrambleElement.setTextSize(28);
    }

    //MOVE TO BUSINESS CLASS? We could pass calc into methods in the business class and have them return strings
    public void averageToDisplay() {
        String averageTime;
       int size = timeList.getSize();
        CalculateAverages calc = new CalculateAverages(size, timeList);


        if (size > 0) {
            averageTime = calc.calcAverage();
            averageText = (TextView) findViewById(R.id.AverageTime);
            averageText.setText(averageTime);
        }
        if(size >=5){
            averageTime = calc.calcAve5();
            averageText = (TextView) findViewById(R.id.AverageTimeOf5);
            averageText.setText(averageTime);
        }
        if(size >=12){
            averageTime = calc.calcAve12();
            averageText = (TextView) findViewById(R.id.AverageTimeOf12);
            averageText.setText(averageTime);
        }
        if(size >=50){
            averageTime = calc.calcAve50();
            averageText = (TextView) findViewById(R.id.AverageTimeOf50);
            averageText.setText(averageTime);
        }
    }

    private void timer() {

        start = (Button)findViewById(R.id.startTimer);
        stop = (Button)findViewById(R.id.stopTimer);
        textview = (TextView) findViewById(R.id.TimerDisplay);

        //DO COUNTDOWN IN THE TIMER CLASS
        handler = new Handler();
//        public boolean fifSecCountdown = false;
//        if(!fifSecCountdown) {
//            new CountDownTimer(15000, 1000){
//                public void onTick(long millisUntilFinished){
//                    textview.setText(String.valueOf(counter));
//                    counter--;
//                }
//                public  void onFinish(){
//                    fifSecCountdown = true;
//                    return;
//                }
//            }.start();
//        }



        start.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                start.setVisibility(View.INVISIBLE);
                stop.setVisibility(View.VISIBLE);
                stopWatch.start(SystemClock.uptimeMillis(), 0);
                handler.postDelayed(runnable, 0);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timeList.add(stopWatch.updateTime(SystemClock.uptimeMillis()));
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                handler.removeCallbacks(runnable);
                scrambleToDisplay();
                averageToDisplay();

            }
        });

    } //end timer

    public Runnable runnable = new Runnable() {

        public void run() {
            textview.setText(stopWatch.toString(stopWatch.updateTime(SystemClock.uptimeMillis())));
            handler.postDelayed(this, 0);
        }

    };

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);

        } catch (IOException ioe) {
            System.out.println("Error");
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}
