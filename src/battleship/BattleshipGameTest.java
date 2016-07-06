package battleship;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BattleshipGameTest {
    BattleshipGame game;

    /**
     * setup method for BattleshipGameTest
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        game = new BattleshipGame();
    }

    /**
     * test method for {@link battleship.BattleshipGame#checkValidInput(String)}
     */
    @Test
    public void testCheckValidInput() {
        String input1 = "gSwE eFa dasdf";
        String input2 = "a b    ";
        String input3 = "12 3";
        String input4 = "2 6";
        assertFalse(game.checkValidInput(input1));
        assertFalse(game.checkValidInput(input2));
        assertFalse(game.checkValidInput(input3));
        assertTrue(game.checkValidInput(input4));
    }

    /**
     * test method for {@link battleship.BattleshipGame#getCoordinates(String)}
     */
    @Test
    public void testGetCoordinates() {
        String input = "2 6";
        int[] coordinates = game.getCoordinates(input);
        assertEquals(2, coordinates[0]);
        assertEquals(6, coordinates[1]);
    }

}
