package sep.myapplication.business;

import junit.framework.TestCase;

/**
 * Created by mark on 2018-02-14.
 */

public class MovesTest extends TestCase {

    //@Test
    public void testExpectedValues() throws Exception{
        assertEquals(Moves.L, Moves.valueOf(0));
        assertEquals(Moves.B, Moves.valueOf(6));
        assertNotSame(Moves.F, Moves.valueOf(12));
    }

    //@Test
    public void testUnexpectedValues() throws Exception{
        assertNull(Moves.valueOf(93));
        assertNull(Moves.valueOf(-1));
    }

}
