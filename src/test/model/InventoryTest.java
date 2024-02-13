package model;

import model.tools.Inventory;
import model.tools.TypingList;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    Inventory tester;

    @BeforeEach
    void setUp() {
        tester = new Inventory();
    }


    @Test
    void testInventory() {
        assertEquals(0, tester.length());
    }


    @Test
    void addRandomItemTest() {
        assertEquals(0, tester.length());

        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();

        assertNotEquals(0, tester.length());
        assertNotNull(tester.getItem(0));
    }


    @Test
    void useItemTest() {
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();

        assertNotNull(tester.useItem(0));
    }
}
