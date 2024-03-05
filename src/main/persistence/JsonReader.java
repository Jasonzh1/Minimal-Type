package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


import model.tools.Inventory;
import org.json.*;

// Reader that interprets an inventory from JSON data in a file
public class JsonReader {
    private final String[] source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String[] source) {
        this.source = source;
    }

    // REQUIRES: 0 <= select < source.length
    // EFFECTS: reads inventory from file and returns it
    // throws IOException if error occurs during reading
    public Inventory read(int select) throws IOException {
        String jsonData = readFile(source[select]);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInventory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // Taken from JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Inventory parseInventory(JSONObject jsonObject) {
        Inventory inventory = new Inventory();
        addItems(inventory, jsonObject);
        return inventory;
    }

    // MODIFIES: wr
    // EFFECTS: parses items from JSON object and adds them to inventory
    private void addItems(Inventory inventory, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addItem(inventory, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses item from JSON object and adds it to inventory
    private void addItem(Inventory inventory, JSONObject jsonObject) {
        String name = jsonObject.getString("type");
        Item item;
        switch (name) {
            case "Cherry MX Blue":
                item = new CherryBlue();
                break;
            case "Cherry MX Brown":
                item = new CherryBrown();
                break;
            case "Cherry MX Red":
                item = new CherryRed();
                break;
            default:
                return;
        }
        inventory.addItem(item);
    }
}
