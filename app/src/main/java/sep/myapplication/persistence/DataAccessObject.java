package sep.myapplication.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
            System.out.println("Connection Successful");
        }
        catch (Exception e)
        {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
        System.out.println("Opened " +dbType + "db " +dbPath);
    }

    public String close()
    {
        try
        {	// commit all changes to the database
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
            System.out.println("Update Successful");
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        return ("Database Closed.");
    }

    public int getSize(){
        int count = 0;
        result = null;

        try
        {
//            cmdString = "SELECT * FROM Times";
//            rs2 = st1.executeQuery(cmdString);
//            //ResultSetMetaData md2 = rs2.getMetaData();
//            while (rs2.next()){
//                count++;
//            }
            cmdString = "SELECT COUNT(*) FROM Times";
            rs2 = st1.executeQuery(cmdString);
            count =  ((Number) rs2.getObject(1)).intValue();
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
            cmdString = "SELECT * FROM Times WHERE Time>=0";
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
            cmdString = "INSERT INTO Times (Time) VALUES(" + values + ")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
            timeCount++;
            System.out.println("Add Successful");
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
            cmdString = "DELETE FROM Times WHERE Time=" +values;
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

    }

    public void modify(long oldT, long newT){
        String oldTime, newTime;

        result = null;
        try
        {
             oldTime = Long.toString(oldT);
             newTime = Long.toString(newT);

            cmdString = "UPDATE Times SET Time =" +newTime + "WHERE (Time=" +oldTime+")";
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
            cmdString = "DELETE FROM Times";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            processSQLError(e);

        }
    }

    public int getIndex(long time){
        ArrayList<Long> list = getList();
        return list.indexOf(time);
    }
    public long getTime(int index){
        ArrayList<Long> list = getList();
        return list.get(index);
    }

    public void addTestValues(){
        long[] testValues = {10000, 20000, 30000, 40000, 50000};

        for (int i = 0; i < testValues.length; i++){
            add(testValues[i]);
        }
    }

    public void addTestValues(int addNumbers){
        long testValues = 10000;

        for (int i = 0; i < addNumbers; i++){
            testValues += 100;
            add(testValues);
        }
    }

    public long getBest(){
        int time;
        long bestTime = -1;

        try
        {
            cmdString = "SELECT * FROM Times";
            rs2 = st2.executeQuery(cmdString);
            while (rs2.next()){
                time = rs2.getInt("Time");
                if ((time < bestTime) || (bestTime == -1)){
                    bestTime = time;
                }
            }
        }
        catch (Exception e)
        {
            processSQLError(e);
        }

        return bestTime;
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
