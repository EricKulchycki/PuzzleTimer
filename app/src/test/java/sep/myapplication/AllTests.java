package sep.myapplication;

/**
 * Created by marky on 2018-02-13.
 */
import org.junit.Test;

public class AllTests {
   @Test
   public void testSuite() throws Exception{
       DataAccessStubTest dasTest = new DataAccessStubTest();
       ScrambleGeneratorTest sgTest = new ScrambleGeneratorTest();
       TimerTest tTest = new TimerTest();

        //Data Access Stub Tests
       dasTest.initialValuesTest();
       dasTest.dummyValuesTest();
       dasTest.modifyDummyValuesTest();
       dasTest.clearListTest();

        //Scramble Generator Tests
       sgTest.genScrambleTest();
       sgTest.genRanNumTest();
       sgTest.scrambleTranslationTest();

        //Timer Tests
       tTest.initialValuesTest();
       tTest.oneSecondTest();
    }
}

