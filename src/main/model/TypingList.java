package model;


import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;


// Represents list of excerpts that players will type
public class TypingList {
    private final List<String> excerpts;
    private final List<String> authors;
    private static final Random random = new Random();

    // MODIFIES: this
    // EFFECTS: Initializes new TypingList
    public TypingList(Path exceprt, Path author) throws IOException {
        excerpts = Files.readAllLines(exceprt);
        authors = Files.readAllLines(author);
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
