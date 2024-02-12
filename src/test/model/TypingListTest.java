package model;

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
    void getNewExcerptTest() {
        assertNotNull(tester.getNewExcerpt());
        assertNotNull(tester.getNewExcerpt().getParagraph());
        assertNotNull(tester.getNewExcerpt().getNextCharacter());
    }

}
