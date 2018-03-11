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

    DataAccessStub testList = new DataAccessStub("testList");

    @Test
    public void testCalculatingAverages(){

        testList.open("testDB");

        CalculateAverages testObj = new CalculateAverages(testList.getSize(), testList);

        assertEquals("Average: 0:14.655", testObj.calcAverage());
        assertEquals("Average of 5: 0:14.644", testObj.calcAve5());
        assertEquals("Average of 12: 0:14.720", testObj.calcAve12());
        assertEquals("", testObj.calcAve50());

    }

    @Test
    public void testEmptyDB(){
        testList.open("testDB");
        testList.reset();

        CalculateAverages testObj = new CalculateAverages(testList.getSize(), testList);

        assertEquals("", testObj.calcAverage());
        assertEquals("", testObj.calcAve5());
        assertEquals("", testObj.calcAve12());
        assertEquals("", testObj.calcAve50());


    }


}
