package sep.myapplication.business;


import android.widget.TextView;

import sep.myapplication.R;
import sep.myapplication.persistence.DataAccessObject;
import sep.myapplication.persistence.DataAccessStub;
import sep.myapplication.persistence.DatabaseInterface;

public class CalculateAverages {

    String averageTime;
    int count = 0, size;

    private long avg, avg5, avg12, avg50;
    private DatabaseInterface list;
    private int Seconds,Minutes;

    public CalculateAverages(int size, DatabaseInterface list) {
        avg = 0;
        avg5= 0;
        avg12=0;
        avg50 = 0;
        long temp = 0;
        this.size = size;
        this.list = list;
    }

    public String calcAverage() {

        if(size > 0) {

            for (int i = 0; i < size; i++) {
                avg += list.getTime(i);
                if (i >= (size - 5)) {
                    avg5 += list.getTime(i);
                }
                if (i >= (size - 12)) {
                    avg12 += list.getTime(i);
                }
                if (i >= (size - 50)) {
                    avg50 += list.getTime(i);
                }
            }
            avg = avg / list.getSize();
            avg5 = avg5 / 5;
            avg12 = avg12 / 12;
            avg50 = avg50 / 50;
        }

        if(size >=1){
            return "Average: " + Timer.toString(avg);
        }
        else
            return "";
    }

    public String calcAve5() {
        if(size >=5){
            return "Average of 5: " + Timer.toString(avg5);
        }
        else
            return "";

    }

    public String calcAve12() {
        if(size >=12){
            return "Average of 12: " + Timer.toString(avg12);
        }
        else
            return "";

    }

    public String calcAve50() {
        if(size >=50){
            return "Average of 50: " + Timer.toString(avg50);
        }
        else
            return "";
    }

    /*
    private void doAvgCalcs() {
        Minutes = Seconds / 60;
        Seconds = Seconds % 60;
    }
    */
}
