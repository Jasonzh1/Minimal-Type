package model;

// Paragraph that appears in the typing environment. Keeps track of current typing position.
public class Paragraph {
    private String paragraph;
    private final String author;
    private int index;
    private int wordCount;

    // MODIFIES: this
    // EFFECTS: Initializes paragraph with input and author, counts number of words
    public Paragraph(String paragraph, String author) {
        this.paragraph = paragraph;
        this.author = author;
        this.index = 0;
        this.wordCount = 1;

        for (int i = 0; i < paragraph.length(); i++) {
            if (paragraph.charAt(i) == ' ') {
                wordCount++;
            }
        }
    }

    // EFFECTS: Returns the next character to be typed by the player
    public char getNextCharacter() {
        return paragraph.charAt(index);
    }

    // MODIFIES: this
    // EFFECTS: Increases index by 1 (Moves typing position forward). Returns
    // true if paragraph is finished, false otherwise.
    public boolean typeCharacter() {
        index++;
        return (index >= paragraph.length());
    }

    // EFFECTS: Returns whether the paragraph has been fully typed
    public boolean isFinished() {
        return (index >= paragraph.length());
    }

    // EFFECTS: Returns substring of paragraph that is typed
    public String getTypedPortion() {
        return this.paragraph.substring(0, index);
    }

    // EFFECTS: Returns substring of paragraph that is not yet typed
    public String getUntypedPortion() {
        return this.paragraph.substring(index);
    }

    // MODIFIES: this
    // EFFECTS: Removes all periods from paragraph
    public void removePeriods() {
        paragraph = paragraph.replace(".", "");
    }

    public int getIndex() {
        return index;
    }

    public int getWordCount() {
        return wordCount;
    }

    public String getParagraph() {
        return paragraph;
    }

    public String getAuthor() {
        return author;
    }
}
