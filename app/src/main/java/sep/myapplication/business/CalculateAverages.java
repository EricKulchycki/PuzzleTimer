package sep.myapplication.business;


import android.widget.TextView;

import sep.myapplication.R;
import sep.myapplication.persistence.DataAccessStub;

public class CalculateAverages {

    String averageTime;
    int count = 0, size;

    private long avg, avg5, avg12, avg50;
    private DataAccessStub list;
    private int Seconds,Minutes;



    public CalculateAverages(int size, DataAccessStub list) {
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

        if (size > 0) {
            Seconds = (int) avg / 1000;
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            avg = avg % 1000;

            averageTime = "" + Minutes + ":" + String.format("%02d", Seconds) + "." + String.format("%03d", avg);
            averageTime = "Average: " + averageTime;
            return averageTime;
        }
        else
            return "";
    }

    public String calcAve5() {

        if(size >=5){

            Seconds = (int) avg5 / 1000;
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            avg5 = avg5 % 1000;

            averageTime = "" + Minutes + ":" + String.format("%02d", Seconds) + "." + String.format("%03d", avg5);
            averageTime = "Average of 5: " + averageTime;
            return averageTime;
        }
        else
            return "";

    }

    public String calcAve12() {

        if(size >=12){
            Seconds = (int) avg12 / 1000;
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            avg12 = avg12 % 1000;

            averageTime = "" + Minutes + ":" + String.format("%02d", Seconds) + "." + String.format("%03d", avg12);
            averageTime = "Average of 12: " + averageTime;
            return averageTime;
        }
        else
            return "";

    }

    public String calcAve50() {

        if(size >=50){
            Seconds = (int) avg50 / 1000;
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            avg50 = avg50 % 1000;

            averageTime = "" + Minutes + ":" + String.format("%02d", Seconds) + "." + String.format("%03d", avg50);
            averageTime = "Average of 50: " + averageTime;
            return averageTime;
        }
        else
            return "";



    }
}
