package sep.myapplication;

class Timer {

    private long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    private int Seconds, Minutes, MilliSeconds;

    private long startTime;
    private long currentTime;

    Timer(){
        startTime = 0;
        currentTime = 0;
    }

    public long getStartTime(){return startTime;}
    public long getCurrentTime(){return currentTime;}

    public long start() {

        MillisecondTime = 0L ;
        StartTime = 0L ;
        TimeBuff = 0L ;
        UpdateTime = 0L ;
        Seconds = 0 ;
        Minutes = 0 ;
        MilliSeconds = 0 ;

        startTime = System.currentTimeMillis();

        return startTime;
    }

    public long run() {
        currentTime = System.currentTimeMillis();
        return (currentTime - startTime);
    }

    public void reset(){
        startTime = 0;
        currentTime = 0;
    }

    public String toString(){


        MilliSeconds = (int)run();

        if (MilliSeconds <= 0){
            return("0:00:00");
        } else {
            Seconds = MilliSeconds / 1000;
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            MilliSeconds = MilliSeconds % 1000;

            return ("" + Minutes + ":" + String.format("%02d", Seconds) + "." + String.format("%02d", MilliSeconds));
        }
    }


}
