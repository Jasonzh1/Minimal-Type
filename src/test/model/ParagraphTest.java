package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class ParagraphTest {
    Paragraph tester;

    @BeforeEach
    void setTester() {
        tester = new Paragraph("Hello World!", "Jason Zheng, CPSC 210");
    }

    @Test
    void getNextCharacterTest() {
        assertEquals('H', tester.getNextCharacter());
        assertFalse(tester.typeCharacter());
        assertEquals('e', tester.getNextCharacter());
        assertFalse(tester.typeCharacter());
        assertEquals('l', tester.getNextCharacter());
    }

    @Test
    void typeCharacter() {
        assertEquals('H', tester.getNextCharacter());
        assertFalse(tester.typeCharacter());
        assertFalse(tester.typeCharacter());
        assertFalse(tester.typeCharacter());
        assertFalse(tester.typeCharacter());
        assertFalse(tester.typeCharacter());
        assertEquals(' ', tester.getNextCharacter());
        assertFalse(tester.typeCharacter());
        assertFalse(tester.typeCharacter());
        assertFalse(tester.typeCharacter());
        assertFalse(tester.typeCharacter());
        assertFalse(tester.typeCharacter());
        assertFalse(tester.typeCharacter());
        assertTrue(tester.typeCharacter());
        assertTrue(tester.isFinished());
    }

    @Test
    void getWordCountTest() {
        Paragraph tester2 = new Paragraph("Wow this is so cool!", "Jason");
        assertEquals(2, tester.getWordCount());
        assertEquals(5, tester2.getWordCount());
    }

}
