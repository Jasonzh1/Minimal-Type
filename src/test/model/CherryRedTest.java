package model;


import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CherryRedTest {
    CherryRed cherryRed;

    @BeforeEach
    void setCherryBlue() {
        cherryRed = new CherryRed();
    }

    @Test
    void testCherryBlue() {
        assertEquals("Cherry MX Red", cherryRed.getName());
        assertEquals("Begin game with 15 characters already typed.", cherryRed.getDescription());
    }


    @Test
    void performAbilityTest() throws IOException {
        Game game = new Game();
        game.initializeGame(1);
        assertEquals(0, game.getPosition());

        cherryRed.perfromAbility(game);

        assertEquals( 15, game.getPosition());
    }
}
