package sep.myapplication;

import junit.framework.Test;
import junit.framework.TestSuite;

import sep.myapplication.business.CalculateAveragesTest;
import sep.myapplication.business.ScrambleGeneratorTest;
import sep.myapplication.business.TimerTest;
import sep.myapplication.persistence.DataAccessStubTest;



public class AllTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testAll();
        return suite;
    }

    private static void testAll()
    {
        suite.addTestSuite(CalculateAveragesTest.class);
        suite.addTestSuite(DataAccessStubTest.class);
        suite.addTestSuite(TimerTest.class);
        suite.addTestSuite(ScrambleGeneratorTest.class);
    }
}