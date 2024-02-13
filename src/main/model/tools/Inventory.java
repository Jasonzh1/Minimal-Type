package model.tools;

import model.CherryBlue;
import model.CherryBrown;
import model.CherryRed;
import model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Inventory {
    private List<Item> items;
    private final Random random = new Random();

    // EFFECTS: Initializes inventory with no items
    public Inventory() {
        items = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: 75% chance of a random item added to inventory. True if item is added, false otherwise
    public boolean addRandomItem() {
        int num = random.nextInt(4);

        switch (num) {
            case 0:
                items.add(new CherryBlue());
                break;
            case 1:
                items.add(new CherryBrown());
                break;
            case 2:
                items.add(new CherryRed());
                break;
            default:
                return false;
        }
        return true;
    }

    // REQUIRES: 0 <= index < items.size()
    // MODIFIES: this
    // EFFECTS: Removes item from list. Returns that item.
    public Item useItem(int index) {
        return items.remove(index);
    }

    // EFFECTS: Returns length of inventory
    public int length() {
        return items.size();
    }

    // REQUIRES: 0 <= index < items.size()
    // MODIFIES: this
    // EFFECTS: Returns item at index.
    public Item getItem(int index) {
        return items.get(index);
    }
}
