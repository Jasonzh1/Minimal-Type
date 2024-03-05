package model;


import org.json.JSONObject;
import persistence.Writable;

// Abstract class representing an item
public abstract class Item implements Writable {
    private final String name;
    private final String description;

    // MODIFIES: this
    // EFFECTS: Creates Item with name and abilities
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // REQUIRES: Game that just entered 'inGame' with no calls to tick()
    // MODIFIES: game
    // EFFECTS: Performs specified ability on game
    public abstract void perfromAbility(Game game);

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", name);
        return json;
    }

}
