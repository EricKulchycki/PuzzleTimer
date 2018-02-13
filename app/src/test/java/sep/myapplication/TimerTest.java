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
    Timer timerTest = new Timer();

    @Test
    public void initialValuesTest() throws Exception{
        assertEquals(0, timerTest.getCurrentTime());
        assertEquals(0, timerTest.getStartTime());
        assertEquals(0, timerTest.getElapsedTime());
    }

    @Test
    public void processingTest() throws Exception{
        long time;

        //due to hardware speeds, it is impossible to perfectly check for a specific elapsed time between using sleep() and the next method calls;
        timerTest.start(System.currentTimeMillis());
        Thread.sleep(1000);

        time = timerTest.run(System.currentTimeMillis());
        assertTrue(time > 1000L);
        assertTrue(time < 1100L); //test for 0.1sec accuracy

        time = timerTest.getCurrentTime() - timerTest.getStartTime();
        assertTrue(time > 1000L);
        assertTrue(time < 1100L); //test for 0.1sec accuracy

        time = timerTest.stop();
        assertTrue(time > 1000L);
        assertTrue(time < 1100L); //test for 0.1sec accuracy

        timerTest.reset();
        initialValuesTest();
    }

}
