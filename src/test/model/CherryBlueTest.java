package model;


import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CherryBlueTest {
    Item cherryBlue;

    @BeforeEach
    void setCherryBlue() {
        cherryBlue = new CherryBlue();
    }

    @Test
    void testCherryBlue() {
        assertEquals("Cherry MX Blue", cherryBlue.getName());
        assertEquals("Gives 10 seconds of extra time.", cherryBlue.getDescription());
    }


    @Test
    void performAbilityTest() throws IOException {
        Game game = new Game();
        game.initializeGame(1);
        int tempTime = game.getTimer();
        assertEquals(tempTime, game.getTimer());

        cherryBlue.perfromAbility(game);

        assertEquals(tempTime + 10, game.getTimer());
    }
}
