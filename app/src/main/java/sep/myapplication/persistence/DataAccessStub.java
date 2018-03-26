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
        list = new ArrayList<>();
    }

    public String close() {
        list = null;
        return "Database Closed.";
    }

    public int getSize() {
        return list.size();
    }
    public ArrayList<Long> getList() {
        return list;
    }
    public String getDbName() {return dbName;}

    public void add(long time) {
        list.add(time);
    }
    public void delete(long time) {
        list.remove(time);
    }
    public void modify(long oldT, long newT){
        return;
    }
    public void reset() {
        list.clear();
    }
    public int getIndex(long time) {
        return list.indexOf(time);
    }
    public long getTime(int index) {
        return list.get(index);
    }

    public long getBest(){
        long bestTime = -1;
        long listTime;

        for (int i = 0; i < list.size(); i++){
            listTime = list.get(i);

            if ((listTime < bestTime) || (bestTime == -1)){
                bestTime = listTime;
            }
        }

        return bestTime;
    }

    public void addTestValues(){
        list.add((long)10000);
        list.add((long)20000);
        list.add((long)30000);
        list.add((long)40000);
        list.add((long)50000);
    }

    public void addTestValues(int addNumbers){
        long testValues = 10000;

        for (int i = 0; i < addNumbers; i++){
            testValues += 100;
            add(testValues);
        }
    }

}
