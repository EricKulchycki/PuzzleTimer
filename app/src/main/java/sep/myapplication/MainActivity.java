package sep.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.SystemClock;


public class MainActivity extends AppCompatActivity {

    private TextView tempTextView; //Temporary TextView
    private Button tempBtn; //Temporary Button
    private Handler mHandler = new Handler();
    private long startTime;
    private long elapsedTime;
    private final int REFRESH_RATE = 10;
    private String minutes,seconds,milliseconds;
    private long secs,mins,msecs;
    private boolean stopped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        scrambleToDisplay();

//        Button startstop;
//        Handler touchScreen = new Handler();
//
//        startstop = (Button) findViewById(R.id.startTimer);
//        startstop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                time = SystemClock.uptimeMillis();
//            }
//        });

    }

    //Displays the scramble on the screen
    public void scrambleToDisplay() {
        ScrambleGenerator scrambleGen = new ScrambleGenerator();
        String scramble = scrambleGen.genScramble();

        TextView scrambleElement;

        scrambleElement = (TextView) findViewById(R.id.scramble);
        scrambleElement.setText(R.string.scramble_text);
        scrambleElement.setText(scramble);
    }

    public void startClick(View view) {
        showStopButton();

        if(stopped){
            startTime = System.currentTimeMillis() - elapsedTime;
        }
        else{
            startTime = System.currentTimeMillis();
        }
        mHandler.removeCallbacks(startTimer);
        mHandler.postDelayed(startTimer, 0);
    }

    public void stopClick(View view) {
        showStartButton();

        mHandler.removeCallbacks(startTimer);
        stopped = true;
    }

    private void showStartButton() {
        ((Button)findViewById(R.id.startTimer)).setVisibility(View.VISIBLE);
        ((Button)findViewById(R.id.stopTimer)).setVisibility(View.GONE);
    }

    private void showStopButton() {
        ((Button)findViewById(R.id.startTimer)).setVisibility(View.GONE);
        ((Button)findViewById(R.id.stopTimer)).setVisibility(View.VISIBLE);
    }

    private void updateTimer (float time){
        secs = (long)(time/1000);
        mins = (long)((time/1000)/60);

		/* Convert the seconds to String
		 * and format to ensure it has
		 * a leading zero when required
		 */
        secs = secs % 60;
        seconds=String.valueOf(secs);
        if(secs == 0){
            seconds = "00";
        }
        if(secs <10 && secs > 0){
            seconds = "0"+seconds;
        }

		/* Convert the minutes to String and format the String */

        mins = mins % 60;
        minutes=String.valueOf(mins);
        if(mins == 0){
            minutes = "00";
        }
        if(mins <10 && mins > 0){
            minutes = "0"+minutes;
        }

    	/* Although we are not using milliseconds on the timer in this example
    	 * I included the code in the event that you wanted to include it on your own
    	 */
        milliseconds = String.valueOf((long)time);
        if(milliseconds.length()==2){
            milliseconds = "0"+milliseconds;
        }
        if(milliseconds.length()<=1){
            milliseconds = "00";
        }
        milliseconds = milliseconds.substring(milliseconds.length()-3, milliseconds.length()-2);

		/* Setting the timer text to the elapsed time */
        ((TextView)findViewById(R.id.TimerDisplay)).setText(minutes + ":" + seconds + "." + milliseconds);
        //((TextView)findViewById(R.id.timerMs)).setText("." + milliseconds);
    }

    private Runnable startTimer = new Runnable() {
        public void run() {
            elapsedTime = System.currentTimeMillis() - startTime;
            updateTimer(elapsedTime);
            mHandler.postDelayed(this,REFRESH_RATE);
        }
    };


}
