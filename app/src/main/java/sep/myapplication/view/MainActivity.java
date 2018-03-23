package sep.myapplication.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import sep.myapplication.persistence.DatabaseInterface;

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
    Button inspection, start, stop;
    Handler handler;
    public int counter;
    Timer stopWatch = new Timer();
    private int selectedSession = 3;
    ArrayAdapter<String> adapter;
    DatabaseInterface timeList;


    final int INSPEC_DELAY = 15000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        copyDatabaseToDevice();
        Main.startUp();


        timeList = Services.createDataAccess(Main.dbName);
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

        MenuItem puzzleTypeMenuItem = menu.findItem(R.id.puzzleType);
        final Spinner puzzleTypeSpinner = (Spinner) puzzleTypeMenuItem.getActionView();
        setupPuzzleSpinner(puzzleTypeSpinner);
        puzzleTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = puzzleTypeSpinner.getItemAtPosition(puzzleTypeSpinner.getSelectedItemPosition()).toString();

                if(text.equals("2x2x2")) {
                    selectedSession = 2;
                } else if(text.equals("3x3x3")) {
                    selectedSession = 3;
                }
                scrambleToDisplay();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        MenuItem ColourMenuItem = menu.findItem(R.id.changeColour);
        final Spinner colourSpinner = (Spinner) ColourMenuItem.getActionView();
        setupColourSpinner(colourSpinner);
        final View root = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        colourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = colourSpinner.getItemAtPosition(colourSpinner.getSelectedItemPosition()).toString();

                if(text.equals("Yellow")) {
                    root.setBackgroundResource(R.color.yellow);
                } else if(text.equals("Blue")) {
                    root.setBackgroundResource(R.color.blue);
                } else if(text.equals("Purple")) {
                    root.setBackgroundResource(R.color.purple);
                } else if(text.equals("Green")) {
                    root.setBackgroundResource(R.color.green);
                }else {
                    root.setBackgroundResource(R.color.white);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return true;
    }

    public void setupPuzzleSpinner(Spinner spin) {
        String[] items = {"3x3x3", "2x2x2"};
        //wrap the items in the Adapter
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, items);
        //assign adapter to the Spinner
        spin.setAdapter(adapter);

    }

    public void setupColourSpinner(Spinner spin) {
        String[] items = {"White", "Yellow", "Blue" ,"Purple","Green"};
        //wrap the items in the Adapter
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, items);
        //assign adapter to the Spinner
        spin.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        System.out.println(id == R.id.puzzleType);
        if (id == R.id.timeList) {
            Intent intent = new Intent(this, TimeListActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.graphDisplay) {
            Intent intent = new Intent(this, GraphActivity.class);
            startActivity(intent);
            return true;
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

        inspection = (Button)findViewById(R.id.inspection);
        start = (Button)findViewById(R.id.startTimer);
        stop = (Button)findViewById(R.id.stopTimer);
        textview = (TextView) findViewById(R.id.TimerDisplay);
        handler = new Handler();

        inspection.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                inspection.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                stopWatch.start(SystemClock.uptimeMillis(), INSPEC_DELAY);
                textview.setTextColor(Color.RED); //
                handler.postDelayed(inspect, 0);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                handler.removeCallbacks(inspect);
                textview.setTextColor(Color.BLACK);
                inspection.setVisibility(View.INVISIBLE);
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
                inspection.setVisibility(View.VISIBLE);
                start.setVisibility(View.INVISIBLE);
                stop.setVisibility(View.INVISIBLE);
                handler.removeCallbacks(runnable);
                scrambleToDisplay();
                averageToDisplay();

            }
        });

    } //end timer

    public Runnable runnable = new Runnable() {

        public void run() {
            long currTime = stopWatch.updateTime(SystemClock.uptimeMillis());
            textview.setText(stopWatch.toString(currTime));
            handler.postDelayed(this, 0);
        }

    };

    //
    public Runnable inspect = new Runnable() {

        public void run() {
            long currTime = stopWatch.updateTime(SystemClock.uptimeMillis());

            if (currTime <= 0){
                textview.setText(stopWatch.toString(currTime));
                handler.postDelayed(this, 0);
            } else {
                start.performClick();
            }
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
