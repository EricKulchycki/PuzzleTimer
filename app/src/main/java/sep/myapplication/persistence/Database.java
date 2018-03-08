package sep.myapplication.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database implements DatabaseInterface {

    public void open(){
        // Load the JDBC Driver
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Establish the connection
        try {
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:timeListDB", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSize() {

        return 0;
    }

    public String getDbName() {
        return "";
    }

    public void add(long time) {
    }
    public void delete(long time) {
    }
    public void reset() {
    }
    public int getIndex(long time) {
        return 0;
    }
    public long getTime(int index) {
        return 0;
    }

}
