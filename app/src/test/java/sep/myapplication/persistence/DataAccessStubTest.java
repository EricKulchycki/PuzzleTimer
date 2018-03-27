package sep.myapplication.persistence;

import org.junit.Test;
import junit.framework.TestCase;

public class DataAccessStubTest extends TestCase {


    public DataAccessStubTest(String arg0)
    {
        super(arg0);
    }

    static DatabaseInterface DASTest = new DataAccessStub("testDB");

    @Test
    public void testInitialValues() throws Exception {
        DASTest.close();
        assertNull(DASTest.getList());
        assertEquals("testDB", DASTest.getDbName());
        try{
            assertEquals(0, DASTest.getSize());
            fail();
        } catch (NullPointerException n){}

        try{
            DASTest.add(10000);
            fail();
        } catch (NullPointerException n){}

        try{
            DASTest.delete(10000);
            fail();
        } catch (NullPointerException n){}

        try{
            DASTest.reset();
            fail();
        } catch (NullPointerException n){}

        try{
            DASTest.getTime(1);
            fail();
        } catch (NullPointerException n){}

        try{
            DASTest.getIndex(1);
            fail();
        } catch (NullPointerException n){}

        assertEquals("Database Closed.", DASTest.close());
    }

    @Test
    public void testInitializationValues() throws Exception{
        DASTest.open("testDB");
        assertNotNull(DASTest.getList());
        assertEquals("testDB", DASTest.getDbName());
        assertEquals(0, DASTest.getSize());
        assertEquals(-1, DASTest.getIndex(1));

        try{
            assertEquals(-1, DASTest.getTime(1));
            fail();
        } catch (IndexOutOfBoundsException i){}
        DASTest.reset();
    }

    @Test
    public void testDummyValues() throws Exception{
        DASTest.open("testDB");
        DASTest.addTestValues();

        assertEquals(5, DASTest.getSize());
        assertEquals(4, DASTest.getIndex(50000));
        assertEquals(50000, DASTest.getTime(4));
        DASTest.reset();
    }

    @Test
    public void testModifyValues() throws Exception{
        DASTest.open("testDB");

        DASTest.add(12345);
        assertEquals(1, DASTest.getSize());
        assertEquals(0, DASTest.getIndex(12345));
        assertEquals(12345, DASTest.getTime(0));

        DASTest.add(11036);
        assertEquals(2, DASTest.getSize());
        assertEquals(1, DASTest.getIndex(11036));
        assertEquals(11036, DASTest.getTime(1));

        DASTest.delete(12345);
        assertEquals(1, DASTest.getSize());
        assertEquals(0, DASTest.getIndex(11036));
        assertEquals(11036, DASTest.getTime(0));

        //delete non-value from list
        DASTest.delete(42069);
        assertEquals(1, DASTest.getSize());
        assertEquals(0, DASTest.getIndex(11036));
        assertEquals(11036, DASTest.getTime(0));
        DASTest.reset();
    }

    @Test
    public void testResetList() throws Exception {
        DASTest.open("testDB");
        DASTest.reset();
        assertEquals(0, DASTest.getSize());

        DASTest.delete(42069);
        assertEquals(0, DASTest.getSize());

        DASTest.add(42069);
        assertEquals(1, DASTest.getSize());
        assertEquals(0, DASTest.getIndex(42069));
        assertEquals(42069, DASTest.getTime(0));

        DASTest.reset();
        assertEquals("Database Closed.", DASTest.close());
    }

    @Test
    public void testSizeAfterModify() throws Exception {
        DASTest.open("testDB");
        DASTest.addTestValues(16);

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

    @Test
    public void testModifyValuesInList() throws Exception{
        DASTest.open("testDB");

        DASTest.addTestValues();
        assertEquals(5, DASTest.getSize());
        assertEquals(10000, DASTest.getTime(0));
        assertEquals(20000, DASTest.getTime(1));
        assertEquals(30000, DASTest.getTime(2));
        assertEquals(40000, DASTest.getTime(3));
        assertEquals(50000, DASTest.getTime(4));

        DASTest.modify(25131, 5000);
        assertNotSame(5000, DASTest.getTime(0));
        assertNotSame(5000, DASTest.getTime(1));
        assertNotSame(5000, DASTest.getTime(2));
        assertNotSame(5000, DASTest.getTime(3));
        assertNotSame(5000, DASTest.getTime(4));


        DASTest.modify(10000, 5000);
        assertNotSame(10000, DASTest.getTime(0));
        assertEquals(5000, DASTest.getTime(0));
    }
}

