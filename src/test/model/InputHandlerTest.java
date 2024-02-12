package model;

import model.tools.InputHandler;
import model.tools.Paragraph;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class InputHandlerTest {
    InputHandler tester;
    List<Paragraph> testExcerpts = new ArrayList<>();

    @BeforeEach
    void setUp() {
        tester = new InputHandler();
        testExcerpts.add(new Paragraph("Hello.", "Jason"));
        testExcerpts.add(new Paragraph("What's up!", "JJ"));
        testExcerpts.add(new Paragraph("How are you?", "Dennis"));
    }

    @Test
    void setCurrentParagraphTest() {
        assertNull(tester.getCurrentParagraph());
        tester.setCurrentParagraph(testExcerpts.get(0));
        assertEquals(testExcerpts.get(0), tester.getCurrentParagraph());
        tester.setCurrentParagraph(testExcerpts.get(1));
        assertEquals(testExcerpts.get(1), tester.getCurrentParagraph());
    }

    @Test
    void handleInputTest() {
        tester.setCurrentParagraph(testExcerpts.get(0));
        assertFalse(tester.handleInput('h'));
        assertFalse(tester.handleInput('d'));

        assertTrue(tester.handleInput('H'));
        assertTrue(tester.handleInput('e'));
        assertTrue(tester.handleInput('l'));

        assertFalse(tester.handleInput('F'));

        assertTrue(tester.handleInput('l'));
        assertTrue(tester.handleInput('o'));
        assertTrue(tester.handleInput('.'));
        assertNull(tester.getCurrentParagraph());
        assertTrue(testExcerpts.get(0).isFinished());
    }

    @Test
    void handleInputTest2() {
        tester.setCurrentParagraph(testExcerpts.get(1));
        assertFalse(tester.handleInput('h'));
        assertFalse(tester.handleInput('d'));

        assertTrue(tester.handleInput('W'));
        assertTrue(tester.handleInput('h'));
        assertTrue(tester.handleInput('a'));

        assertFalse(tester.handleInput('F'));

        assertTrue(tester.handleInput('t'));
        assertTrue(tester.handleInput('\''));
        assertTrue(tester.handleInput('s'));
        assertTrue(tester.handleInput(' '));
        assertTrue(tester.handleInput('u'));
        assertTrue(tester.handleInput('p'));
        assertTrue(tester.handleInput('!'));

        assertNull(tester.getCurrentParagraph());
        assertTrue(testExcerpts.get(1).isFinished());
    }
}
