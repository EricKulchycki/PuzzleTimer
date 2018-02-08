package sep.myapplication;

// imports
import java.util.ArrayList;

public class Database {
    private ArrayList list;

    public Database() {
        list = new ArrayList();
    }
    public void add(float time) {
        list.add(time);
    }
    public void delete(float time) {
        list.remove(time);
    }
    public void reset() {
        list.clear();
    }

    public int trialNum(float time) {
        return list.indexOf(time);
    }
    public float getTime(int index) {
        return (float)list.get(index);
    }
    public int getSize() {
        return list.size();
    }

    public float average() {
        float totalTime, avg;
        int size;

        totalTime = 0;
        size = list.size();
        for (int i =0; i < size; i++) {
            totalTime += (float) list.get(i);
        }
        avg = totalTime / size;
        return avg;
    }

    public float bestTime() {
        float bestTime, time;

        bestTime = -1;
        for (int i =0; i < list.size(); i++) {
            time = (float) list.get(i);
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
    public float worstTime() {
        float worstTime, time;

        worstTime = -1;
        for (int i =0; i < list.size(); i++) {
            time = (float) list.get(i);
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
