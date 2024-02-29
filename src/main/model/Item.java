package model;


// Abstract class representing an item
public abstract class Item {
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

    public abstract void perfromAbility(Game game);

}
