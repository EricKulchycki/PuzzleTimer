package sep.myapplication.objects;

import java.util.ArrayList;

import sep.myapplication.Application.Main;
import sep.myapplication.Application.Services;
import sep.myapplication.persistence.*;

public class Timer {

    private long startTime;
    private DatabaseInterface timeList;

    public Timer(DatabaseInterface DI){
        startTime = 0;
        timeList = DI;
    }

    //list functions
    public long getStartTime(){return startTime;}
    public DatabaseInterface getDatabase(){return timeList;}
    public ArrayList getList(){return timeList.getList();}
    public int getSize(){return timeList.getSize();}
    public void add(long l){timeList.add(l);}
    public void delete(long l){timeList.delete(l);}
    public long getBest(){
        return timeList.getBest();
    }
    public void reset(){ timeList.reset();}

    //timer functions
    public long start(long time, long delay) {
        startTime = time + delay;
        return startTime;
    }

    public long updateTime(long time) {
        return time - startTime;
    }

    public void stop(long time){add(time);}
    public static String toString(long time){
        int MilliSeconds, Seconds, Minutes = 0;

        MilliSeconds = (int)Math.abs(time);

        Seconds = MilliSeconds / 1000;
        Minutes = Seconds / 60;
        Seconds = Seconds % 60;
        MilliSeconds = MilliSeconds % 1000;

        return ("" + Minutes + ":" + String.format("%02d", Seconds) + "." + String.format("%03d", MilliSeconds));
    }

}
