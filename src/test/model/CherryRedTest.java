package model;


import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CherryRedTest {
    CherryRed cherryRed;
    Game testGame;
    Game testGame2;

    @BeforeEach
    void setCherryBlue() throws IOException{
        cherryRed = new CherryRed();
        testGame = new Game();
        testGame2 = new Game();
        testGame.initializeGame(1);
        testGame2.initializeGame(3);
    }

    @Test
    void testCherryBlue() {
        assertEquals("Cherry MX Red", cherryRed.getName());
        assertEquals("Begin game with 15 characters already typed.", cherryRed.getDescription());
    }


    @Test
    void performAbilityTest() {
        assertEquals(0, testGame.getPosition());
        assertEquals(0, testGame2.getPosition());

        cherryRed.perfromAbility(testGame);
        cherryRed.perfromAbility(testGame2);

        assertEquals( 15, testGame.getPosition());
        assertEquals( 15, testGame2.getPosition());

    }
}
