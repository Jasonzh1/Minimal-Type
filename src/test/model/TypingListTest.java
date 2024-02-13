package model;

import model.tools.TypingList;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;

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
        assertEquals(Files.readAllLines(Path.of("src/excerpts.txt")), tester.getExcerpts());
        assertEquals(Files.readAllLines(Path.of("src/authors.txt")), tester.getAuthors());
    }


    @Test
    void getNewExcerptTest() {
        assertTrue(tester.getExcerpts().contains(tester.getNewExcerpt().getUntypedPortion()));
        assertTrue(tester.getExcerpts().contains(tester.getNewExcerpt().getUntypedPortion()));

    }


    @Test
    void getExcerptsTest() throws IOException {
        assertEquals(10, tester.getExcerpts().size());
        assertEquals(Files.readAllLines(Path.of("src/excerpts.txt")), tester.getExcerpts());
    }


    @Test
    void getAuthorsTest() throws IOException{
        assertEquals(10, tester.getAuthors().size());
        assertEquals(Files.readAllLines(Path.of("src/authors.txt")), tester.getAuthors());
    }
}
