package model.tools;

import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;


// Represents list of excerpts that players will type
public class TypingList {
    private final List<String> excerpts = Files.readAllLines(Path.of("src/excerpts.txt"));
    private final List<String> authors = Files.readAllLines(Path.of("src/authors.txt"));
    private final Random random = new Random();

    // MODIFIES: this
    // EFFECTS: Initializes new TypingList
    public TypingList() throws IOException {

    }

    // EFFECTS: Returns random excerpt from list as a Paragraph
    public Paragraph getNewExcerpt() {
        int num = random.nextInt(10);
        return new Paragraph(excerpts.get(num), authors.get(num));
    }

    public List<String> getExcerpts() {
        return excerpts;
    }

    public List<String> getAuthors() {
        return authors;
    }

}
