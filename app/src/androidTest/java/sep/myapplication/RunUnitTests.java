package sep.myapplication;

import junit.framework.Test;
import junit.framework.TestSuite;

import sep.myapplication.business.CalculateAveragesTest;
import sep.myapplication.business.MovesTest;
import sep.myapplication.business.ScrambleGeneratorTest;
import sep.myapplication.business.TimerTest;
import sep.myapplication.persistence.DataAccessStubTest;



public class RunUnitTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Unit tests");
        testAll();
        return suite;
    }

    private static void testAll()
    {
        suite.addTestSuite(DataAccessStubTest.class);
        suite.addTestSuite(CalculateAveragesTest.class);
        suite.addTestSuite(TimerTest.class);
        suite.addTestSuite(ScrambleGeneratorTest.class);
        suite.addTestSuite(MovesTest.class);
    }
}