package model;

import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game tester;

    @BeforeEach
    void setUp() throws IOException{
        tester = new Game();
    }

    @Test
    void gameTest() {
        assertFalse(tester.isInGame());
    }

    @Test
    void initializeGameTest() {
        tester.initializeGame(1);

        assertNotNull(tester.getUntypedPortion());
        assertEquals("", tester.getTypedPortion());
        assertNotNull(tester.getAuthor());

        assertNotEquals(-1, tester.getTimer());
        assertTrue(tester.isInGame());
        assertFalse(tester.isEnded());
    }

    @Test
    void tickTest() {
        tester.initializeGame(2);
        int currentTime = tester.getTimer();

        for (int i = 0; i < 60; i++) {
            tester.tick();
        }

        assertEquals(currentTime-1, tester.getTimer());
        assertTrue(tester.isInGame());
    }
}
