package sep.myapplication.view;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.SystemClock;

import sep.myapplication.R;
import sep.myapplication.business.CalculateAverages;
import sep.myapplication.business.ScrambleGenerator;
import sep.myapplication.business.Timer;
import sep.myapplication.persistence.DataAccessStub;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {


    TextView textview,averageText;
    Button start, stop;
    Handler handler;
    public int counter;
    Timer stopWatch = new Timer();

    //Database where times are stored
    DataAccessStub timeList = new DataAccessStub("timeList");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        if(savedInstanceState != null) {

            long[] temp = savedInstanceState.getLongArray("tempA");
            System.out.println(temp.length);

            for(int i = 0; i < temp.length; i++) {
                timeList.add(temp[i]);
            }
        }
        else {
            timeList.open();
        }

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action) {
            Intent intent = new Intent(this, TimeListActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Displays the scramble on the screen
    public void scrambleToDisplay() {
        ScrambleGenerator scrambleGen = new ScrambleGenerator();
        String scramble = scrambleGen.genScramble();

        TextView scrambleElement;

        scrambleElement = (TextView) findViewById(R.id.scramble);
        scrambleElement.setText(R.string.scramble_text);
        scrambleElement.setText(scramble);
        scrambleElement.setTextSize(28);
    }

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
                stopWatch.start(SystemClock.uptimeMillis());
                handler.postDelayed(runnable, 0);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timeList.add(stopWatch.run(SystemClock.uptimeMillis()));
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
            textview.setText(stopWatch.toString(stopWatch.run(SystemClock.uptimeMillis())));
            handler.postDelayed(this, 0);
        }

    };
}
