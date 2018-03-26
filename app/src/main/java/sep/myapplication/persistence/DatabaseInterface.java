package sep.myapplication.persistence;


import java.sql.SQLException;
import java.util.ArrayList;

public interface DatabaseInterface {

    public void open(String s);

    public int getSize();

    public ArrayList<Long> getList();

    public String getDbName();
    public void add(long time);
    public void delete(long time);
    public void modify(long oldT, long newT);
    public void reset();
    public int getIndex(long time);
    public long getTime(int index);
    public long getBest();
    public String close();
    public void addTestValues();

}
