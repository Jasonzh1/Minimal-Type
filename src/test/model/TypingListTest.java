package model;

import model.tools.TypingList;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TypingListTest {
    TypingList tester;

    @BeforeEach
    void setTester() throws IOException{
        tester = new TypingList();
    }


    @Test
    void typingListTest() throws IOException {
        assertNotNull(tester.getExcerpts());
        assertNotNull(tester.getAuthors());
    }


    @Test
    void getNewExcerptTest() {
        assertNotEquals("", tester.getNewExcerpt().getParagraph());
        assertNotEquals(' ', tester.getNewExcerpt().getNextCharacter());
    }


    @Test
    void getExceprtsTest() {
        assertEquals(10, tester.getExcerpts().size());
    }


    @Test
    void getAuthorsTest() {
        assertEquals(10, tester.getAuthors().size());
    }
}
