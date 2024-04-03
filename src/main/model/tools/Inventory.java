package model.tools;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Keeps an inventory of current items and adds new items
public class Inventory implements Writable {
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
        EventLog events = EventLog.getInstance();

        switch (num) {
            case 0:
                items.add(new CherryBlue());
                events.logEvent(new Event("Completing level added new Cherry Blue item to Inventory"));
                break;
            case 1:
                items.add(new CherryBrown());
                events.logEvent(new Event("Completing level added new Cherry Brown item to Inventory"));
                break;
            case 2:
                items.add(new CherryRed());
                events.logEvent(new Event("Completing level added new Cherry Red item to Inventory"));
                break;
            default:
                return false;
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: Removes item at index
    public void removeItem(int index) {
        EventLog events = EventLog.getInstance();
        events.logEvent(new Event("User deleted " + items.get(index).getName() + " item from Inventory"));
        items.remove(index);
    }

    // MODIFIES: this
    // EFFECTS: Adds given item to end of inventory
    public void addItem(Item item) {
        items.add(item);
    }

    // REQUIRES: 0 <= index < items.size()
    // MODIFIES: this
    // EFFECTS: Removes item from list. Returns that item.
    public Item useItem(int index) {
        EventLog events = EventLog.getInstance();
        events.logEvent(new Event("User consumed " + items.get(index).getName() + " item from Inventory"));
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

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("items", itemsToJson());
        return json;
    }

    // EFFECTS: Returns all items in invetory as JSON Array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : items) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }
}
