package sep.myapplication.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database implements DatabaseInterface {

    public void open() throws ClassNotFoundException, SQLException {
        // Load the JDBC Driver
        Class.forName("org.hsqldb.jdbcDriver");
        // Establish the connection
        Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:timeListDB", "sa", "");
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
