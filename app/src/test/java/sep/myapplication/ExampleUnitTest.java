package sep.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void initialValuesTest() throws Exception {
        Timer tTest = new Timer();
        assertEquals(0, tTest.getCurrentTime());
        assertEquals(0, tTest.getStartTime());
        assertEquals("0:00:00", tTest.toString());
    }
}