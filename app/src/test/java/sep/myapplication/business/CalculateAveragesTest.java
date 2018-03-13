package sep.myapplication.business;


import sep.myapplication.persistence.DataAccessStub;
import org.junit.Test;
import junit.framework.TestCase;

import java.sql.SQLException;


public class CalculateAveragesTest extends TestCase{

    public CalculateAveragesTest(String arg0)
    {
        super(arg0);
    }
    CalculateAverages testObj;
    DataAccessStub testList = new DataAccessStub("testList");

    @Test
    public void test1number(){

        testList.open("testDB");
        testList.add(1000);

        testObj = new CalculateAverages(testList.getSize(), testList);

        assertEquals("Average: 0:01.000", testObj.calcAverage());
        assertEquals("", testObj.calcAve5());
        assertEquals("", testObj.calcAve12());
        assertEquals("", testObj.calcAve50());

    }

    @Test
    public void test5numbers(){

        testList.open("testDB");
        testList.addTestValues();

        testObj = new CalculateAverages(testList.getSize(), testList);

        assertEquals("Average: 0:30.000", testObj.calcAverage());
        assertEquals("Average of 5: 0:30.000", testObj.calcAve5());
        assertEquals("", testObj.calcAve12());
        assertEquals("", testObj.calcAve50());

    }

    @Test
    public void test12numbers(){

        testList.open("testDB");
        testList.addTestValues(12);

        testObj = new CalculateAverages(testList.getSize(), testList);

        assertEquals("Average: 0:10.650", testObj.calcAverage());
        assertEquals("Average of 5: 0:11.000", testObj.calcAve5());
        assertEquals("Average of 12: 0:10.650", testObj.calcAve12());
        assertEquals("", testObj.calcAve50());

    }

    @Test
    public void test50numbers(){

        testList.open("testDB");
        testList.addTestValues(51);

        testObj = new CalculateAverages(testList.getSize(), testList);

        assertEquals("Average: 0:12.600", testObj.calcAverage());
        assertEquals("Average of 5: 0:14.900", testObj.calcAve5());
        assertEquals("Average of 12: 0:14.550", testObj.calcAve12());
        assertEquals("Average of 50: 0:12.650", testObj.calcAve50());

    }

    @Test
    public void testEmptyDB(){
        testList.open("testDB");
        testList.reset();

        testObj = new CalculateAverages(testList.getSize(), testList);

        assertEquals("", testObj.calcAverage());
        assertEquals("", testObj.calcAve5());
        assertEquals("", testObj.calcAve12());
        assertEquals("", testObj.calcAve50());
    }


}
