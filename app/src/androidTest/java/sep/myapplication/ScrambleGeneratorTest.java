package sep.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ScrambleGeneratorTest {

    ScrambleGenerator testScramble = new ScrambleGenerator();

    @Test
    public void genScrambleTest() {
        assertTrue(testScramble.genScramble() != null);
    }
}