package sep.myapplication.integration;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.util.ArrayList;

import sep.myapplication.Application.Main;
import sep.myapplication.Application.Services;
import sep.myapplication.objects.Timer;
import sep.myapplication.persistence.*;

public class TimerDatabaseIT extends TestCase {


    public TimerDatabaseIT(String arg0)
    {
        super(arg0);
    }

    static DataAccessStub DASTest = new DataAccessStub(Main.dbName);
    DatabaseInterface DBTest;
    static Timer TTest;

    @Test
    public void testAddValues() throws Exception{
        DASTest.open(Main.dbName);
        TTest = new Timer(DASTest);

       //add values using stop methods
        TTest.stop(1000);
        TTest.stop(2000);
        TTest.stop(3000);
        TTest.stop(4000);
        TTest.stop(5000);

        //add values to db using Timer's add method
        TTest.add(6000);
        TTest.add(7000);
        TTest.add(8000);
        TTest.add(9000);
        TTest.add(10000);

       //test methods in which timer interacts with database

        assertEquals(10, TTest.getSize());
        assertEquals(1000, TTest.getBest());

        //check for valid database
        DBTest = TTest.getDatabase();
        assertEquals(1000, DBTest.getTime(0));
        assertEquals(2000, DBTest.getTime(1));
        assertEquals(3000, DBTest.getTime(2));
        assertEquals(4000, DBTest.getTime(3));
        assertEquals(5000, DBTest.getTime(4));
        assertEquals(6000, DBTest.getTime(5));
        assertEquals(7000, DBTest.getTime(6));
        assertEquals(8000, DBTest.getTime(7));
        assertEquals(9000, DBTest.getTime(8));
        assertEquals(10000, DBTest.getTime(9));
        assertEquals(0, DBTest.getIndex(1000));
        assertEquals(1, DBTest.getIndex(2000));
        assertEquals(2, DBTest.getIndex(3000));
        assertEquals(3, DBTest.getIndex(4000));
        assertEquals(4, DBTest.getIndex(5000));
        assertEquals(5, DBTest.getIndex(6000));
        assertEquals(6, DBTest.getIndex(7000));
        assertEquals(7, DBTest.getIndex(8000));
        assertEquals(8, DBTest.getIndex(9000));
        assertEquals(9, DBTest.getIndex(10000));


        //check for values not in database
        try{
            assertEquals(11000, DBTest.getTime(10));
            assertEquals(-1, DBTest.getIndex(0));
            fail();
        } catch (NullPointerException n){
        } catch (IndexOutOfBoundsException i){}

        TTest.reset();
    }

    @Test
    public void testRemoveValues() throws Exception{
        DASTest.open(Main.dbName);
        TTest = new Timer(DASTest);

        //add values to db using Timer's add method
        TTest.add(1000);
        TTest.add(2000);
        TTest.add(3000);
        TTest.add(4000);
        TTest.add(5000);

        //test methods in which timer interacts with database
        assertEquals(5, TTest.getSize());
        assertEquals(1000, TTest.getBest());

        //remove values using timer's delete method
        TTest.delete(1000);
        TTest.delete(5000);

        //test methods in which timer interacts with database
        assertEquals(3, TTest.getSize());
        assertEquals(2000, TTest.getBest());

        //validate database
        DBTest = TTest.getDatabase();
        assertEquals(2000, (DBTest.getTime(0)));
        assertEquals(3000, (DBTest.getTime(1)));
        assertEquals(4000, (DBTest.getTime(2)));

        TTest.reset();
    }
}

