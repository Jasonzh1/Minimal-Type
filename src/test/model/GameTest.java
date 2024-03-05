package model;

import model.tools.Paragraph;
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
    }

    @Test
    void tickTest() {
        tester.initializeGame(2);
        int currentTime = tester.getTimer();

        for (int i = 0; i < 45; i++) {
            tester.tick();
        }

        assertEquals(currentTime - 1, tester.getTimer());
        assertTrue(tester.isInGame());
    }

    @Test
    void tickTest2() {
        tester.initializeGame(2);
        int currentTime = tester.getTimer();

        for (int i = 0; i < currentTime * 45; i++) {
            tester.tick();
        }

        assertEquals(0, tester.getTimer());
        assertFalse(tester.isInGame());
    }

    @Test
    void tickTest3() {
        tester.initializeGame(1);
        Paragraph currentParagraph = tester.getCurrentParagraph();

        for (int i = 0; i < 500; i++) {
            currentParagraph.typeCharacter();
        }
        tester.tick();
        assertFalse(tester.isInGame());
    }


    @Test
    void handleInputTest() {
        tester.initializeGame(1);
        tester.handleInput('d');
        assertTrue(tester.isInGame());
    }

    @Test
    void resetTimerTest() {
        assertEquals(-1, tester.getTimer());
        tester.initializeGame(1);
        assertNotEquals(-1, tester.getTimer());

        tester.resetTimer();
        assertEquals(-1, tester.getTimer());
    }

    @Test
    void getAuthorTest() {
        tester.initializeGame(1);
        assertNotEquals("", tester.getAuthor());
    }

    @Test
    void getCurrentParagraphTest() {
        assertNull(tester.getCurrentParagraph());
        tester.initializeGame(1);
        assertNotNull(tester.getCurrentParagraph());
    }
}
