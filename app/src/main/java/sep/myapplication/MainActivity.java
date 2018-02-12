package sep.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.SystemClock;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Arrays;
import java.util.List;

import sep.myapplication.sep.myapplication.persistence.DataAccessStub;


public class MainActivity extends AppCompatActivity {

    TextView textview;
    Button start, stop, reset, lap ;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;
    ListView listView ;
    String[] ListElements = new String[] {  };
    List<String> ListElementsArrayList ;
    ArrayAdapter<String> adapter ;
    Timer stopWatch = new Timer();

    //Database where times are stored
    DataAccessStub timeList = new DataAccessStub("timeList");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeList.open();

        scrambleToDisplay();
        timer();

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

                MillisecondTime = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;
                Seconds = 0 ;
                Minutes = 0 ;
                MilliSeconds = 0 ;


                start.setVisibility(View.INVISIBLE);
                stop.setVisibility(View.VISIBLE);

                StartTime = SystemClock.uptimeMillis();

                handler.postDelayed(runnable, 0);

                //??start.setVisibility(View.INVISIBLE);
                //??stop.setVisibility(View.VISIBLE);
                //??stopWatch.start();
                //??handler.postDelayed(runnable, 0);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimeBuff += MillisecondTime;

                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);

                handler.removeCallbacks(runnable);

                String finalTime = textview.getText().toString();

                //Once timer is stopped, add time to timeList
                timeList.add(UpdateTime);

                scrambleToDisplay();

                //??start.setVisibility(View.VISIBLE);
                //??stop.setVisibility(View.INVISIBLE);
                //??timeList.addTime(stopWatch.toString());
                //??scrambleToDisplay();

            }
        });



    } //end timer

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            Seconds = (int) (UpdateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            MilliSeconds = (int) (UpdateTime % 1000);

            String displayString = "" + Minutes + ":" + String.format("%02d", Seconds) + "." + String.format("%03d", MilliSeconds);

            textview.setText(displayString);

            handler.postDelayed(this, 0);

            //?? textview.setText(stopWatch.toString());
            //?? handler.postDelayed(this, 0);
        }

    };




}
