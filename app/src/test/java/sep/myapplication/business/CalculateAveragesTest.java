package sep.myapplication.business;

import org.junit.Test;

import sep.myapplication.business.CalculateAverages;
import sep.myapplication.business.Timer;
import sep.myapplication.persistence.DataAccessStub;

import static org.junit.Assert.*;

public class CalculateAveragesTest {

    DataAccessStub testList = new DataAccessStub("testList");

    @Test
    public void calculatingAveragesTest() {

        testList.open();

        CalculateAverages testObj = new CalculateAverages(testList.getSize(), testList);

        assertEquals("Average: 0:14.655", testObj.calcAverage());
        assertEquals("Average of 5: 0:14.644", testObj.calcAve5());
        assertEquals("Average of 12: 0:14.720", testObj.calcAve12());
        assertEquals("", testObj.calcAve50());

    }

    @Test
    public void emptyDBTest() {
        testList.open();
        testList.reset();

        CalculateAverages testObj = new CalculateAverages(testList.getSize(), testList);

        assertEquals("", testObj.calcAverage());
        assertEquals("", testObj.calcAve5());
        assertEquals("", testObj.calcAve12());
        assertEquals("", testObj.calcAve50());


    }


}
