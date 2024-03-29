package model;

import model.tools.Inventory;
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
    void testRemoveItem() {
        tester.addItem(new CherryBrown());
        tester.removeItem(0);
        assertEquals(0, tester.length());
    }

    @Test
    void addRandomItemTest() {
        assertEquals(0, tester.length());

        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
        tester.addRandomItem();
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
        tester.addRandomItem();

        assertNotNull(tester.useItem(0));
    }

    @Test
    void addItemTest() {
        Item temp = new CherryBrown();
        tester.addItem(temp);
        assertEquals(1, tester.length());
        assertEquals(temp, tester.getItem(0));
    }

    @Test
    void toJsonTest() {
        tester.addItem(new CherryBrown());
        assertEquals("{\"items\":[{\"type\":\"Cherry MX Brown\"}]}",
                tester.toJson().toString());
    }
}
