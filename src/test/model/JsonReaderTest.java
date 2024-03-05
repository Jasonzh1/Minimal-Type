package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    JsonReader tester;

    @BeforeEach
    void setUp() {
        String[] testFiles = {"data/testNoItems.json", "data/testTwoItems.json"};
        tester = new JsonReader(testFiles);
    }

    @Test
    void testRead() {
        try {
            assertEquals(0, tester.read(0).length());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testRead2() {
        try {
            assertEquals(2, tester.read(1).length());
            assertTrue(tester.read(1).getItem(0) instanceof CherryBlue);
            assertTrue(tester.read(1).getItem(1) instanceof CherryRed);
        } catch (IOException e) {
            fail();
        }
    }
}
