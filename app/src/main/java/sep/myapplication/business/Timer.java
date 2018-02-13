package sep.myapplication.business;

public class Timer {

    private long startTime;
    private long currentTime;
    private long elapsedTime;

    public Timer(){
        startTime = 0;
        currentTime = 0;
        elapsedTime = 0;
    }

    public long getStartTime(){return startTime;}
    public long getCurrentTime(){return currentTime;}
    public long getElapsedTime(){return elapsedTime;}

    public long start(long time) {
        startTime = time;
        return startTime;
    }

    public long run(long time) {
        currentTime = time;
        return currentTime - startTime;
    }

    public long stop(){
        elapsedTime += (currentTime - startTime);
        return elapsedTime;
    }

    public void reset(){
        startTime = 0;
        currentTime = 0;
    }

    public String toString(long time){
        int MilliSeconds, Seconds, Minutes = 0;

        MilliSeconds = (int)time;

        if (MilliSeconds <= 0){
            return("0:00:00");
        } else {
            Seconds = MilliSeconds / 1000;
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            MilliSeconds = MilliSeconds % 1000;

            return ("" + Minutes + ":" + String.format("%02d", Seconds) + "." + String.format("%03d", MilliSeconds));
        }
    }

}
