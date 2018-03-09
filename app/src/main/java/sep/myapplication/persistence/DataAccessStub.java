package sep.myapplication.persistence;

// imports
import java.util.ArrayList;

public class DataAccessStub implements DatabaseInterface {
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Long> list;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public void open() {

        list = new ArrayList<Long>();

        list.add((long)11543);
        list.add((long)14256);
        list.add((long)18514);
        list.add((long)13524);
        list.add((long)14253);
        list.add((long)16284);
        list.add((long)15246);
        list.add((long)12504);
        list.add((long)15246);
        list.add((long)15643);
        list.add((long)14246);
        list.add((long)17564);
        list.add((long)11036);
        list.add((long)16536);
        list.add((long)14524);
        list.add((long)13563);
    }

    public String close()
    {
        return("Closed " + dbType +" database " + dbName);
    }

    public int getSize() {
        return list.size();
    }
    public ArrayList getList() {
        return list;
    }
    public String getDbName() {return dbName;}

    public void add(long time) {
        list.add(time);
    }
    public void delete(long time) {
        list.remove(time);
    }
    public void reset() {
        list.clear();
    }
    public int getIndex(long time) {
        return list.indexOf(time);
    }
    public long getTime(int index) {
        return (long)list.get(index);
    }


}
