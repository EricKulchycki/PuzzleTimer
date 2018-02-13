package sep.myapplication.view;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.SystemClock;

import sep.myapplication.R;
import sep.myapplication.business.ScrambleGenerator;
import sep.myapplication.business.Timer;
import sep.myapplication.persistence.DataAccessStub;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {


    TextView textview,averageText, average5Text, Average12Text, Average50Text;
    Button start, stop, reset, lap ;
    long MillisecondTime, StartTime, TimeBuff, avg, avg5, avg12, avg50, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;
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
        int count = 0, size = timeList.getSize();

        avg = 0;
        avg5= 0;
        avg12=0;
        avg50 = 0;
        long temp = 0;

        for( int i=0;i<size;i++){
            avg+=timeList.getTime(i);
            if( i >= (size-5)){
                avg5+= timeList.getTime(i);
            }
            if(i >= (size-12)){
                avg12 += timeList.getTime(i);
            }
            if(i>=(size-50)){
                avg50 += timeList.getTime(i);
            }
            count++;
        }
        avg = avg/timeList.getSize();
        avg5 = avg5/5;
        avg12 = avg12/12;
        avg50 = avg50/50;

        if (avg > 0) {
            averageTime = String.valueOf(avg/1000);
            averageText = (TextView) findViewById(R.id.AverageTime);
            averageText.setText("Average:" + averageTime);
        }
        if(count >=5){
            averageTime = String.valueOf((avg5/1000));
            averageText = (TextView) findViewById(R.id.AverageTimeOf5);
            averageText.setText("Average of 5:" + averageTime);
        }
        if(count >=12){
            averageTime = String.valueOf(avg12/1000);
            averageText = (TextView) findViewById(R.id.AverageTimeOf12);
            averageText.setText("Average of 12:" + averageTime);
        }
        if(count >=50){
            averageTime = String.valueOf(avg50/1000);
            averageText = (TextView) findViewById(R.id.AverageTimeOf50);
            averageText.setText("Average of 50:" + averageTime);
        }
    }

    private void timer() {

        start = (Button)findViewById(R.id.startTimer);
        stop = (Button)findViewById(R.id.stopTimer);
        textview = (TextView) findViewById(R.id.TimerDisplay);
        //reset = (Button)findViewById(R.id.button3);
        //lap = (Button)findViewById(R.id.button4) ;
        //listView = (ListView)findViewById(R.id.listview1);

        handler = new Handler();

//        ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));
//
//        adapter = new ArrayAdapter<String>(MainActivity.this,
//                android.R.layout.simple_list_item_1,
//                ListElementsArrayList
//        );

        //listView.setAdapter(adapter);

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
