package sep.myapplication;

import org.junit.Test;
import sep.myapplication.business.Timer;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class TimerTest {
    @Test
    public void initialValuesTest() throws Exception{
       Timer tTest = new Timer();
        assertEquals(0, tTest.getCurrentTime());
        assertEquals(0, tTest.getStartTime());
        assertEquals("0:00:00", tTest.toString(tTest.run(System.currentTimeMillis())));
    }

    @Test
    public void processingTest() throws InterruptedException{
        long time;
        Timer tTest = new Timer();

        //due to different hardware speeds, it is impossible to accurately check for a specific elapsed time using sleep(;
        tTest.start(System.currentTimeMillis());
        Thread.sleep(1000);
        time = tTest.run(System.currentTimeMillis());

        assertTrue(time >= 1000L);
        assertTrue(time < 1010L); //test for 0.01sec accuracy

        time = tTest.getCurrentTime() - tTest.getStartTime();
        assertTrue(time >= 1000L);
        assertTrue(time < 1010L); //test for 0.01sec accuracy

        tTest.reset();
    }
}
