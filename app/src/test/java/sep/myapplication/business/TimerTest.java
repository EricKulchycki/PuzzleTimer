package sep.myapplication.business;

import junit.framework.TestCase;

import sep.myapplication.objects.Timer;


public class TimerTest extends TestCase {

    public TimerTest(String arg0)
    {
        super(arg0);
    }
    Timer timerTest = new Timer();

    //@Test
    public void testInitialValues() throws Exception{
        assertEquals(0, timerTest.getStartTime());
    }

    //@Test
    public void testOneSecond() throws Exception{
        long time;

        //due to hardware speeds, it is impossible to perfectly check for a specific elapsed time between using sleep() and the next method calls;
        timerTest.start(System.currentTimeMillis(), 0);
        Thread.sleep(1000);

        time = timerTest.updateTime(System.currentTimeMillis());
        assertTrue(time >= 1000L);
        assertTrue(time < 1100L); //test for 0.1sec accuracy
    }

    //@Test
    public void testFixedValues() throws Exception {
        testInitialValues();
        assertEquals(1500, timerTest.start(1500));
        assertEquals(2500, timerTest.updateTime(4000));
    }

    //@Test
    public void testToString() throws Exception {
        assertEquals("0:00.000", timerTest.toString(-1));
        assertEquals("0:00.000", timerTest.toString(0));
        assertEquals("0:00.001", timerTest.toString(1));
        assertEquals("0:01.000", timerTest.toString(1000));

    }

}
