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
    void typingListTest() {
        assertNotNull(tester.getExcerpts());
        assertNotNull(tester.getAuthors());
    }


    @Test
    void getNewExcerptTest() {
        assertTrue(tester.getExcerpts().contains(tester.getNewExcerpt().getUntypedPortion()));
        assertTrue(tester.getExcerpts().contains(tester.getNewExcerpt().getUntypedPortion()));

    }


    @Test
    void getExcerptsTest() {
        assertEquals(10, tester.getExcerpts().size());
    }


    @Test
    void getAuthorsTest() {
        assertEquals(10, tester.getAuthors().size());
    }
}
