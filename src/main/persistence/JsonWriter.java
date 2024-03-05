package persistence;

import model.tools.Inventory;
import org.json.JSONObject;


import java.io.*;

// Taken from JsonDemo
// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private final String[] destinations;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String[] destinations) {
        this.destinations = destinations;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open(int select) throws FileNotFoundException {
        writer = new PrintWriter(new File(destinations[select]));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(Inventory inventory) {
        JSONObject json = inventory.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
