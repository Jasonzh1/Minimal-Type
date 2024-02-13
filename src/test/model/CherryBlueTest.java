package model;


import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CherryBlueTest {
    CherryBlue cherryBlue;
    Game testGame;
    Game testGame2;

    @BeforeEach
    void setCherryBlue() throws IOException {
        cherryBlue = new CherryBlue();
        testGame = new Game();
        testGame2 = new Game();
        testGame.initializeGame(1);
        testGame2.initializeGame(3);
    }

    @Test
    void testCherryBlue() {
        assertEquals("Cherry MX Blue", cherryBlue.getName());
        assertEquals("Gives 10 seconds of extra time.", cherryBlue.getDescription());
    }


    @Test
    void performAbilityTest() {
        int time1 = testGame.getTimer();
        int time2 = testGame2.getTimer();
        assertNotEquals(-1, time1);
        assertNotEquals(-1, time2);

        cherryBlue.perfromAbility(testGame);
        cherryBlue.perfromAbility(testGame2);

        assertEquals(time1 + 10, testGame.getTimer());
        assertEquals(time2 + 10, testGame2.getTimer());
    }
}
