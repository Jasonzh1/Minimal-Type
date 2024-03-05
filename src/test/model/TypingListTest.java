package model;

import model.tools.TypingList;
import org.junit.jupiter.api.*;

import java.io.IOException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;

public class TypingListTest {
    TypingList tester;
    List<String> excerptTest;
    List<String> authorTest;

    @BeforeEach
    void setTester() throws IOException{
        excerptTest = Files.readAllLines(Path.of("data/tester.txt"));
        authorTest = Files.readAllLines(Path.of("data/authors.txt"));

        tester = new TypingList(Path.of("data/tester.txt"), Path.of("data/authors.txt"));
    }


    @Test
    void typingListTest() {
        assertNotNull(tester.getExcerpts());
        assertNotNull(tester.getAuthors());
        assertEquals(excerptTest, tester.getExcerpts());
        assertEquals(authorTest, tester.getAuthors());
    }


    @Test
    void getNewExcerptTest() {
        assertTrue(excerptTest.contains(tester.getNewExcerpt().getUntypedPortion()));
        assertTrue(authorTest.contains(tester.getNewExcerpt().getAuthor()));

    }


}
