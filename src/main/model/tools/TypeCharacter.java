package model.tools;

// Compares input value to next value in paragraph. Types character if equal.
public class TypeCharacter {
    private Paragraph currentParagraph;

    // EFFECTS: Initializes input handler with no paragraph
    public TypeCharacter() {
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
