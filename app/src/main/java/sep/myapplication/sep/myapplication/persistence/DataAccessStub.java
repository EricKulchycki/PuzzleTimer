package sep.myapplication.sep.myapplication.persistence;

// imports
import java.util.ArrayList;

public class DataAccessStub {
    private ArrayList<Long> list;

    public DataAccessStub() {
    }

    public void open() {
        list = new ArrayList<Long>();
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
    public float getTime(int index) {
        return (float)list.get(index);
    }
    public int getSize() {
        return list.size();
    }

    public long average() {
        long totalTime, avg;
        int size;

        totalTime = 0;
        size = list.size();
        for (int i =0; i < size; i++) {
            totalTime += (float) list.get(i);
        }
        avg = totalTime / size;
        return avg;
    }

    public long bestTime() {
        long bestTime, time;

        bestTime = -1;
        for (int i =0; i < list.size(); i++) {
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
}
