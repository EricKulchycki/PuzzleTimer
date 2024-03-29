package sep.myapplication.business;


import org.junit.Test;
import junit.framework.TestCase;

public class ScrambleGeneratorTest extends TestCase {

    public ScrambleGeneratorTest(String arg0)
    {
        super(arg0);
    }

    ScrambleGenerator testScramble = new ScrambleGenerator();

    @Test
    public void testGenScramble()    {
        assertTrue(testScramble.genScramble(25) != null);
    }

    @Test
    public void testGenRanNum() {
        int ranNum = testScramble.genRanNum();
        assertTrue(ranNum >= 0 && ranNum < 18);
    }


    @Test
    public void testScrambleTranslation() {

        assertEquals("R' U D2 B' ", testScramble.translateScramble("RpUD2Bp"));
        assertEquals(null, testScramble.translateScramble(null));
        assertEquals("", testScramble.translateScramble(""));

        //Testing full length scramble
        String testScramble1 = "F2R2U2B2UpR2UpR2D2UpF2RpULpF2R2DpFU2RUp";
        String expecScramble = "F2 R2 U2 B2 U' R2 U' R2 D2 U' F2 R' U L' F2 R2 D' F U2 R U' ";
        assertEquals(expecScramble, testScramble.translateScramble(testScramble1));

        //Test scrambles with all '2' turns
        assertEquals("R2 L2 D2 F2 D2 ", testScramble.translateScramble("R2L2D2F2D2"));

        //Test scramble with no primes
        assertEquals("R F B R D U F B F ", testScramble.translateScramble("RFBRDUFBF"));

        //Test scrambles with all primes
        assertEquals("R' F' B' R' D' U' F' ", testScramble.translateScramble("RpFpBpRpDpUpFp"));
    }

    @Test
    public void testSmallerScrambleSize() {
        String scrambleSmall = testScramble.genScramble(9);
        assertTrue(scrambleSmall.length() <= 27); //Accounts for spaces and prime or double moves

    }


}
