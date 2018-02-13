package sep.myapplication;

import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

import sep.myapplication.business.ScrambleGenerator;
import sep.myapplication.persistence.DataAccessStub;


public class DataAccessStubTest {
    DataAccessStub DASTest = new DataAccessStub("testStub");

    @Test
    public void initialValuesTest() throws Exception {
        assertNull(DASTest.getList());
        assertEquals("testStub", DASTest.getDbName());
    }

    @Test
    public void dummyValuesTest() throws Exception{
        DASTest.open();
        assertEquals(16, DASTest.getSize());
        assertEquals(5, DASTest.trialNum(16284));
        assertEquals(16284, DASTest.getTime(5));
    }

    @Test
    public void modifyDummyValuesTest() throws Exception{
        DASTest.open();

        DASTest.add(12345);
        assertEquals(17, DASTest.getSize());
        assertEquals(16, DASTest.trialNum(12345));
        assertEquals(12345, DASTest.getTime(16));

        DASTest.delete(11036);
        dummyValuesTest();
    }

    @Test
    public void clearListTest() throws Exception {
        DASTest.open();
        DASTest.reset();
        assertEquals(0, DASTest.getSize());

        assertEquals("Closed stub database testStub", DASTest.close());
    }

}

