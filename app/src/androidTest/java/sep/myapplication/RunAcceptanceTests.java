package sep.myapplication;

import junit.framework.Test;
import junit.framework.TestSuite;

import sep.myapplication.acceptance.ChangeScrambleSizeTest;
import sep.myapplication.acceptance.ModifyDeleteTimesTest;
import sep.myapplication.acceptance.StartTimerTest;
import sep.myapplication.acceptance.TestColourChange;
import sep.myapplication.acceptance.ViewGraphTest;



public class RunAcceptanceTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Acceptance tests");
        testAll();
        return suite;
    }

    private static void testAll()
    {
        suite.addTestSuite(ChangeScrambleSizeTest.class);
        suite.addTestSuite(ModifyDeleteTimesTest.class);
        suite.addTestSuite(StartTimerTest.class);
        suite.addTestSuite(ViewGraphTest.class);
        suite.addTestSuite(TestColourChange.class);
    }
}
