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
        TypingList tester2 = new TypingList();
    }


    @Test
    void getNewExcerptTest() {
        assertNotEquals("", tester.getNewExcerpt().getParagraph());
        assertNotEquals(' ', tester.getNewExcerpt().getNextCharacter());
    }

}
