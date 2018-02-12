package sep.myapplication.persisitence;

// imports
import java.util.ArrayList;

public class DataAccessStub {
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Long> list;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public void open() {
        list = new ArrayList<Long>();
//        list.add((long)640000);
//        list.add((long)320000);
//        list.add((long)180000);
//        list.add((long)90000);
    }

    public String close()
    {
        return("Closed " + dbType +" database " + dbName);
    }

    public void add(long time) {
        list.add(time);
    }
    public void delete(long time) {
        list.remove(time);
    }
    public void reset() {
        list.clear();
    }

    public int trialNum(long time) {
        return list.indexOf(time);
    }
    public long getTime(int index) {
        return (long)list.get(index);
    }
    public int getSize() {
        return list.size();
    }

    //NOTE:

    public long average() {

        long totalTime, avg;
        int size;

        totalTime = 0;
        size = list.size();

        for (int i =0; i < size; i++) {

            totalTime += (long) list.get(i);
        }

        avg = totalTime / size;

        return avg;
    }

    public long bestTime() {

        long bestTime, time;

        bestTime = -1;

        for (int i = 0; i < list.size(); i++) {

            time = (long) list.get(i);

            if (i == 0) {
                bestTime = time;
            } else {

                if(time > bestTime) {
                    bestTime = time;
                }
            }
        }
        return bestTime; // return -1 if the ArrayList is empty
    }
    public long worstTime() {
        long worstTime, time;

        worstTime = -1;

        for (int i =0; i < list.size(); i++) {

            time = (long) list.get(i);

            if (i == 0) {
                worstTime = time;
            } else {

                if(time < worstTime) {
                    worstTime = time;
                }
            }
        }
        return worstTime; // return -1 if the ArrayList is empty
    }

    public String toString(){
        int MilliSeconds, Seconds, Minutes = 0;

        long avg = average();

        if (avg <= 0){
            return("test");
        } else {
            Seconds = (int)avg / 1000;
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            avg = avg % 1000;

            return ("" + Minutes + ":" + String.format("%02d", Seconds) + "." + String.format("%03d", avg));
        }
    }
}
