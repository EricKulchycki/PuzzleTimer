package sep.myapplication.business;


import sep.myapplication.Application.Main;
import sep.myapplication.Application.Services;
import sep.myapplication.objects.Timer;
import sep.myapplication.persistence.DatabaseInterface;

public class CalculateAverages {

    int size;
    private long avg, avg5, avg12, avg50;
    private DatabaseInterface list;

    public CalculateAverages(int size, DatabaseInterface db) {
        avg = 0;
        avg5= 0;
        avg12=0;
        avg50 = 0;
        this.size = size;
        this.list = db;
    }

    public String calcAverage() {

        if(size > 0) {

            for (int i = 0; i < size; i++) {
                avg += list.getTime(i);
                System.out.println(avg);

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
}
