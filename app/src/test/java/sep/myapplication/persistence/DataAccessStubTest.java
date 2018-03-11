package sep.myapplication.persistence;

import org.junit.Test;
import junit.framework.TestCase;

public class DataAccessStubTest extends TestCase {


    public DataAccessStubTest(String arg0)
    {
        super(arg0);
    }


    DataAccessStub DASTest = new DataAccessStub("testStub");

    @Test
    public void testInitialValues() throws Exception {
        DASTest.open("testDB");
        assertNotNull(DASTest.getList());
        assertEquals("testDB", DASTest.getDbName());
    }

    @Test
    public void testDummyValues() throws Exception{
        DASTest.open("testDB");
        assertEquals(16, DASTest.getSize());
        assertEquals(5, DASTest.getIndex(16284));
        assertEquals(16284, DASTest.getTime(5));
    }

    @Test
    public void testModifyDummyValues() throws Exception{
        DASTest.open("testDB");

        DASTest.add(12345);
        assertEquals(17, DASTest.getSize());
        assertEquals(16, DASTest.getIndex(12345));
        assertEquals(12345, DASTest.getTime(16));

        DASTest.delete(11036);
        testDummyValues();

        //delete non-value from list
        DASTest.delete(42069);
        testDummyValues();
    }

    @Test
    public void testClearList() throws Exception {
        DASTest.open("testDB");
        DASTest.reset();
        assertEquals(0, DASTest.getSize());

        DASTest.delete(42069);
        assertEquals(0, DASTest.getSize());

        DASTest.add(42069);
        assertEquals(1, DASTest.getSize());
        assertEquals(0, DASTest.getIndex(42069));
        assertEquals(42069, DASTest.getTime(0));

        assertEquals("Database Closed.", DASTest.close());
    }

    @Test
    public void testSizeAfterModify() throws Exception {
        DASTest.open("testDB");

        assertEquals(16, DASTest.getSize());
        DASTest.add(13645);
        assertEquals(17, DASTest.getSize());
        DASTest.add(17595);
        assertEquals(18, DASTest.getSize());

        //Test remove
        DASTest.delete(13645);
        assertEquals(17, DASTest.getSize());
        DASTest.delete(17595);
        assertEquals(16, DASTest.getSize());
    }

}

