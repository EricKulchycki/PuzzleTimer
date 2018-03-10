package sep.myapplication.business;



public class Timer {

    private long startTime;
    private long currentTime;
    private long elapsedTime;

    public Timer(){
        startTime = 0;
    }

    public long getStartTime(){return startTime;}

    public long start(long time, long delay) {
        startTime = time + delay;
        return startTime;
    }

    public long updateTime(long time) {
        return time - startTime;
    }

    public static String toString(long time){
        int MilliSeconds, Seconds, Minutes = 0;

        MilliSeconds = (int)time;

        if (MilliSeconds <= 0){
            return("0:00.000");
        } else {
            Seconds = MilliSeconds / 1000;
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            MilliSeconds = MilliSeconds % 1000;

            return ("" + Minutes + ":" + String.format("%02d", Seconds) + "." + String.format("%03d", MilliSeconds));
        }
    }

}
