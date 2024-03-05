package persistence;

import org.json.JSONObject;

// Taken from demo
// All implementations must be able to be returned as a JSON Object
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}