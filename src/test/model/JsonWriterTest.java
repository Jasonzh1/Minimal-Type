package model;

import model.tools.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    JsonWriter writer;
    JsonReader reader;

    @BeforeEach
    void setUp() {
        String[] testFiles = {"data/dataSaveTest.json", "data/dataSaveTest2.json"};
        writer = new JsonWriter(testFiles);
        reader = new JsonReader(testFiles);
    }

    @Test
    void testWrite() {
        try {
            writer.open(0);
            writer.write(new Inventory());
            writer.close();
            assertEquals(0, reader.read(0).length());
        } catch (IOException e) {
            fail();
        }

    }

    @Test
    void testWrite2() {
        Inventory input = new Inventory();
        input.addItem(new CherryRed());
        input.addItem(new CherryBrown());

        try {
            writer.open(1);
            writer.write(input);
            writer.close();
            assertEquals(2, reader.read(1).length());
            assertTrue(reader.read(1).getItem(0) instanceof CherryRed);
            assertTrue(reader.read(1).getItem(1) instanceof CherryBrown);
        } catch (IOException e) {
            fail();
        }

    }
}
