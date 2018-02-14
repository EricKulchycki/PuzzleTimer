package sep.myapplication.persistence;

import org.junit.Test;

import junit.framework.TestCase;

import sep.myapplication.persistence.DataAccessStub;


public class DataAccessStubTest extends TestCase {


    public DataAccessStubTest(String arg0)
    {
        super(arg0);
    }


    DataAccessStub DASTest = new DataAccessStub("testStub");

    @Test
    public void testInitialValues() throws Exception {
        assertNotNull(DASTest.getList());
        assertEquals("testStub", DASTest.getDbName());
    }

    @Test
    public void testDummyValues() throws Exception{
        DASTest.open();
        assertEquals(16, DASTest.getSize());
        assertEquals(5, DASTest.trialNum(16284));
        assertEquals(16284, DASTest.getTime(5));
    }

    @Test
    public void testModifyDummyValues() throws Exception{
        DASTest.open();

        DASTest.add(12345);
        assertEquals(17, DASTest.getSize());
        assertEquals(16, DASTest.trialNum(12345));
        assertEquals(12345, DASTest.getTime(16));

        DASTest.delete(11036);
        testDummyValues();

        //delete non-value from list
        DASTest.delete(42069);
        testDummyValues();
    }

    @Test
    public void testClearList() throws Exception {
        DASTest.open();
        DASTest.reset();
        assertEquals(0, DASTest.getSize());

        DASTest.delete(42069);
        assertEquals(0, DASTest.getSize());

        DASTest.add(42069);
        assertEquals(1, DASTest.getSize());
        assertEquals(0, DASTest.trialNum(42069));
        assertEquals(42069, DASTest.getTime(0));

        assertEquals("Closed stub database testStub", DASTest.close());
    }

}

