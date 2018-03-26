package sep.myapplication.business;


import java.util.ArrayList;

import sep.myapplication.objects.Timer;
import sep.myapplication.persistence.DatabaseInterface;

public class CalculateAverages {

    int size;
    private long avg, avg5, avg12, avg50;
    private DatabaseInterface list;

    public CalculateAverages(int size, DatabaseInterface list) {
        avg = 0;
        avg5= 0;
        avg12=0;
        avg50 = 0;
        this.size = size;
        this.list = list;
    }

    public String calcAverage() {
        ArrayList<Long> timeList = list.getList();
        long curr = 0;
        size = 0;

        if(timeList.size() > 0) {

            for (int i = timeList.size() - 1; i >= 0; i--) {
                curr = timeList.get(i);

                if (curr != 0){
                    avg += curr;
                    if (i >= (size - 5)) {
                        avg5 += curr;
                    }
                    if (i >= (size - 12)) {
                        avg12 += curr;
                    }
                    if (i >= (size - 50)) {
                        avg50 += curr;
                    }

                    size++;
                }

            }

            if (size > 0){
                avg = avg / size;
            }

                avg5 = avg5 / 5;
                avg12 = avg12 / 12;
                avg50 = avg50 / 50;
        }
            return "Average: " + Timer.toString(avg);
    }

    public String calcAve5() {
        if (size >= 5)
            return "Average of 5: " + Timer.toString(avg5);

        return "Average of 5: --.---";
    }

    public String calcAve12() {
        if (size >= 12)
            return "Average of 12: " + Timer.toString(avg12);

        return "Average of 12: --.---";

    }

    public String calcAve50() {
        if (size >= 50)
            return "Average of 50: " + Timer.toString(avg50);

        return "Average of 50: --.---";
    }
}
