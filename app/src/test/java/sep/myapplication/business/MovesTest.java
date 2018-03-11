package sep.myapplication.business;

import org.junit.Test;
import junit.framework.TestCase;

import sep.myapplication.objects.Moves;


public class MovesTest extends TestCase {

    @Test
    public void testExpectedValues() throws Exception{
        assertEquals(Moves.L, Moves.valueOf(0));
        assertEquals(Moves.B, Moves.valueOf(6));
        assertNotSame(Moves.F, Moves.valueOf(12));
    }

    @Test
    public void testUnexpectedValues() throws Exception{
        assertNull(Moves.valueOf(93));
        assertNull(Moves.valueOf(-1));
    }

}
