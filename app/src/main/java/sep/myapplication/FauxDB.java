package sep.myapplication;

import java.util.ArrayList;
import java.util.*;


public class FauxDB {

    ArrayList<String> db;

    //Constructor
    public FauxDB() {
        db  = new ArrayList<String>();
    }

    public void addTime(String time) {
        db.add(time);
    }



}
