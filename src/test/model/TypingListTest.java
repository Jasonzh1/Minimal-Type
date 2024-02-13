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
        tester = new TypingList();
        excerptTest = Files.readAllLines(Path.of("src/excerpts.txt"));
        authorTest = Files.readAllLines(Path.of("src/authors.txt"));
    }


    @Test
    void typingListTest() throws IOException {
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
