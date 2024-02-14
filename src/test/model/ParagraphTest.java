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

    @Test
    void isFinishedTest() {
        assertFalse(tester.isFinished());
        for (int i = 0; i < 12; i++) {
            tester.typeCharacter();
        }
        assertTrue(tester.isFinished());
    }

    @Test
    void getTypedPortionTest() {
        assertEquals("", tester.getTypedPortion());
        tester.typeCharacter();
        assertEquals("H", tester.getTypedPortion());
        tester.typeCharacter();
        assertEquals("He", tester.getTypedPortion());
    }

    @Test
    void getUntypedPortionTest() {
        assertEquals("Hello World!", tester.getUntypedPortion());
        tester.typeCharacter();
        assertEquals("ello World!", tester.getUntypedPortion());
        tester.typeCharacter();
        assertEquals("llo World!", tester.getUntypedPortion());
    }

    @Test
    void removePeriodsTest() {
        Paragraph tester2 = new Paragraph("Hello. World.", "Jason");
        assertEquals("Hello World!", tester.getParagraph());
        tester.removePeriods();
        assertEquals("Hello World!", tester.getParagraph());

        assertEquals("Hello. World.", tester2.getParagraph());
        tester2.removePeriods();
        assertEquals("Hello World", tester2.getParagraph());
    }

    @Test
    void getIndexTest() {
        assertEquals(0, tester.getIndex());
        tester.typeCharacter();
        assertEquals(1, tester.getIndex());
    }


    @Test
    void getParagraphTest() {
        assertEquals("Hello World!", tester.getParagraph());
    }

    @Test
    void getAuthorTest() {
        assertEquals("Jason Zheng, CPSC 210", tester.getAuthor());
    }

}
