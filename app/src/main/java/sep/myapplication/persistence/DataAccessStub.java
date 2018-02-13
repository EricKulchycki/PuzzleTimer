package sep.myapplication.persistence;

// imports
import java.util.ArrayList;

public class DataAccessStub {
    private static String dbName;
    private static String dbType = "stub";

    private static ArrayList<Long> list;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public static void open() {
        list = new ArrayList<Long>();
//        list.add((long)640000);
//        list.add((long)320000);
//        list.add((long)180000);
//        list.add((long)90000);
    }

    public static String close()
    {
        return("Closed " + dbType +" database " + dbName);
    }

    public static void add(long time) {
        list.add(time);
    }

    public static void delete(long time) {
        list.remove(time);
    }

    public static void reset() {
        list.clear();
    }

    public static int trialNum(long time) {
        return list.indexOf(time);
    }

    public static long getTime(int index) {
        return (long)list.get(index);
    }

    public static int getSize() {
        return list.size();
    }

    public static ArrayList getList() {
        return list;
    }

    public static long average() {

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

    public static long bestTime() {

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
    public static long worstTime() {
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
}
