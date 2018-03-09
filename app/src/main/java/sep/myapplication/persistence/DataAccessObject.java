package sep.myapplication.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;

public class DataAccessObject implements DatabaseInterface
{
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2;

    private String dbName;
    private String dbType;

    private String cmdString;
    private int updateCount;
    private String result;
    private static int timeCount = 1;

    public DataAccessObject(String dbName)
    {
        this.dbName = dbName;
    }

    public void open(String dbPath)
    {
        String url;
        try
        {
            // Setup for HSQL
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();
        }
        catch (Exception e)
        {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
        System.out.println("Opened " +dbType +" database " +dbPath);
    }

    public String close()
    {
        try
        {	// commit all changes to the database
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        return ("Closed " +dbType +" database " +dbName);
    }

    public int getSize(){
        int count = 0;
        result = null;

        try
        {
            cmdString = "Select Count(*) from Times";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
            rs2.next();
            count = rs2.getInt(1);
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        return count;
    }

    public String getDbName(){return dbName;}

    public ArrayList<Long> getList(){
        ArrayList<Long> retval = new ArrayList<>();
        int time;
        long retvalValue;

        try
        {
            cmdString = "Select * from Times";
            rs2 = st2.executeQuery(cmdString);
            while (rs2.next()){
                time = rs2.getInt("Time");
                retvalValue = time;
                retval.add(retvalValue);
            }
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        return retval;
    }

    public void add(long time){
        String values;

        result = null;
        try
        {
            values = Long.toString(time);
            cmdString = "Insert into Times" + " Values(" + timeCount + "," + values + ")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
            timeCount++;
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
    }

    public void delete(long time){
        String values;

        result = null;
        try
        {
            values = Long.toString(time);
            cmdString = "Delete from Times where Time=" +values;
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

    }
    public void reset(){
        try
        {
            cmdString = "Delete From Times";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
    }

    public int getIndex(long time){
        int index = -1;

        try
        {
            cmdString = "Select * from Times where Time=" + Long.toString(time);
            rs2 = st2.executeQuery(cmdString);
            while (rs2.next()){
                index = rs2.getInt("Run");
            }
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        return index;
    }
    public long getTime(int index){
        int time = -1;

        try
        {
            cmdString = "Select * from Times where Run=" + Long.toString(index);
            rs2 = st2.executeQuery(cmdString);
            while (rs2.next()){
                time = rs2.getInt("Time");
            }
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        return time;
    }

    public String checkWarning(Statement st, int updateCount)
    {
        String result;

        result = null;
        try
        {
            SQLWarning warning = st.getWarnings();
            if (warning != null)
            {
                result = warning.getMessage();
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        if (updateCount != 1)
        {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}
