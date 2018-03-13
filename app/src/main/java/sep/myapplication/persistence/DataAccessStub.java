package sep.myapplication.persistence;

// imports
import java.util.ArrayList;

public class DataAccessStub implements DatabaseInterface {
    private String dbName;
    private ArrayList<Long> list;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public void open(String dbName) {
        this.dbName = dbName;
        list = new ArrayList<Long>();
    }

    public String close() {
        list = null;
        return "Database Closed.";
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

    public void addTestValues(){
        list.add((long)10000);
        list.add((long)20000);
        list.add((long)30000);
        list.add((long)40000);
        list.add((long)50000);
    }

}
