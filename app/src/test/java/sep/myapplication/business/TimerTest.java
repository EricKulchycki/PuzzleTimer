package sep.myapplication.business;

import junit.framework.TestCase;


public class TimerTest extends TestCase {

    public TimerTest(String arg0)
    {
        super(arg0);
    }
    Timer timerTest = new Timer();

    //@Test
    public void testInitialValues() throws Exception{
        assertEquals(0, timerTest.getCurrentTime());
        assertEquals(0, timerTest.getStartTime());
        assertEquals(0, timerTest.getElapsedTime());
    }

    //@Test
    public void testOneSecond() throws Exception{
        long time;

        //due to hardware speeds, it is impossible to perfectly check for a specific elapsed time between using sleep() and the next method calls;
        timerTest.start(System.currentTimeMillis());
        Thread.sleep(1000);

        time = timerTest.run(System.currentTimeMillis());
        assertTrue(time >= 1000L);
        assertTrue(time < 1100L); //test for 0.1sec accuracy

        time = timerTest.getCurrentTime() - timerTest.getStartTime();
        assertTrue(time >= 1000L);
        assertTrue(time < 1100L); //test for 0.1sec accuracy

        time = timerTest.stop();
        assertTrue(time >= 1000L);
        assertTrue(time < 1100L); //test for 0.1sec accuracy

        timerTest.reset();
        testInitialValues();
    }

    //@Test
    public void testFixedValues() throws Exception {
        timerTest.reset();
        testInitialValues();
        assertEquals(1500, timerTest.start(1500));
        assertEquals(2500, timerTest.run(4000));
    }

    //@Test
    public void testToString() throws Exception {
        assertEquals("0:00.000", timerTest.toString(-1));
        assertEquals("0:00.000", timerTest.toString(0));
        assertEquals("0:00.001", timerTest.toString(1));
        assertEquals("0:01.000", timerTest.toString(1000));

    }

}
