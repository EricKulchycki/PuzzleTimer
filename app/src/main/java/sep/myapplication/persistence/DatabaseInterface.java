package sep.myapplication.persistence;


import java.sql.SQLException;
import java.util.ArrayList;

public interface DatabaseInterface {

    public void open() throws ClassNotFoundException, SQLException;

    public int getSize();

   // public ArrayList getList();

    public String getDbName();
    public void add(long time);
    public void delete(long time);
    public void reset();
    public int getIndex(long time);
    public long getTime(int index);




}