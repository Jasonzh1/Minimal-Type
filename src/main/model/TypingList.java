package model;

import java.awt.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;


// Represents list of excerpts that players will type
public class TypingList {
    private List<String> excerpts;
    private List<String> authors;
    private Random random = new Random();

    // MODIFIES: this
    // EFFECTS: Initializes TypingList by importing text file
    public TypingList() throws IOException {
        excerpts = Files.readAllLines(Path.of("src/excerpts.txt"));
        authors = Files.readAllLines(Path.of("src/authors.txt"));
    }

    // EFFECTS: Returns random excerpt from list as a Paragraph
    public Paragraph getNewExcerpt() {
        int num = random.nextInt(10);
        return new Paragraph(excerpts.get(num), authors.get(num));
    }

}
