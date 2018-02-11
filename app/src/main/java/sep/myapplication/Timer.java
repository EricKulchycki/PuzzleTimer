package sep.myapplication;

class Timer {

    private long startTime;
    private long currentTime;

    Timer(){
        startTime = 0;
        currentTime = 0;
    }

    long getStartTime(){return startTime;}
    long getCurrentTime(){return currentTime;}

    void start() {
        startTime = System.currentTimeMillis();
    }

    long run() {
        currentTime = System.currentTimeMillis();
        return (currentTime - startTime);
    }

    void reset(){
        startTime = 0;
        currentTime = 0;
    }

    public String toString(){
        int Seconds, Minutes, MilliSeconds;

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
