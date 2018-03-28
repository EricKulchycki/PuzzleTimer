package sep.myapplication;

import junit.framework.Test;
import junit.framework.TestSuite;


import sep.myapplication.integration.TimerDatabaseIT;
import sep.myapplication.integration.DBInjectionTest;



public class RunIntegrationTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Integration tests");
        testAll();
        return suite;
    }

    private static void testAll()
    {
        suite.addTestSuite(TimerDatabaseIT.class);
        suite.addTestSuite(DBInjectionTest.class);
    }
}
