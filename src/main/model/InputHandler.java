package model;

// Handles user input
public class InputHandler {
    private Paragraph currentParagraph;

    // EFFECTS: Initializes input handler with no paragraph
    public InputHandler() {
        this.currentParagraph = null;
    }

    // MODIFIES: this
    // Sets current paragraph to the provided paragraph
    public void setCurrentParagraph(Paragraph currentParagraph) {
        this.currentParagraph = currentParagraph;
    }

    // MODIFIES: this
    // EFFECTS: Types character for current paragraph. Returns true if progress was made
    // in typing, false otherwise. Empties currentParagraph if finished.
    public boolean handleInput(char c) {
        if (c == currentParagraph.getNextCharacter()) {
            if (currentParagraph.typeCharacter()) {
                currentParagraph = null;
            }
            return true;
        }
        return false;
    }

    public Paragraph getCurrentParagraph() {
        return currentParagraph;
    }
}
