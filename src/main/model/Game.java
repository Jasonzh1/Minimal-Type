package model;

import java.io.IOException;

// Handles game behavior every tick
public class Game {
    public static final int TICKS_PER_SECOND = 60;
    public static final int[] WORDS_PER_MINUTE = {15, 30, 50, 80, 100};

    private boolean inGame;
    private int timer;
    private int counter;

    private boolean gameOver = false;

    private final InputHandler inputHandler;
    private final TypingList typingList;
    private Paragraph currentParagraph;

    // EFFECTS: Initializes a new game in the menu state
    public Game() throws IOException {
        this.inGame = false;
        this.timer = -1;

        this.inputHandler = new InputHandler();
        this.typingList = new TypingList();
        this.currentParagraph = null;
    }

    // REQUIRES: Valid select between 1-6
    // MODIFIES: this
    // EFFECTS: Begins game with new paragraph and sets difficulty. Ends game if 6 is
    // selected
    public void initializeGame(int select) {
        if (select == 6) {
            gameOver = true;
            return;
        }
        currentParagraph = typingList.getNewExcerpt();
        inputHandler.setCurrentParagraph(currentParagraph);

        float temp = (float) currentParagraph.getWordCount() / (float) WORDS_PER_MINUTE[select - 1] * (float) 60;
        timer = (int) temp;
        counter = 0;
        inGame = true;
    }

    // MODIFIES: this
    // EFFECTS: Ticks timer and checks current game state.
    public void tick() {
        counter++;
        if (counter >= 45) {
            timer--;
            counter = 0;
        }

        if (timer <= 0) {
            inGame = false;
            return;
        }

        if (currentParagraph.isFinished()) {
            inGame = false;
        }

    }


    // MODIFIES: this
    // EFFECTS: Uses inputHandler to handle user input
    public void handleInput(char c) {
        inputHandler.handleInput(c);
    }

    public String getAuthor() {
        return currentParagraph.getAuthor();
    }

    public String getTypedPortion() {
        return currentParagraph.getTypedPortion();
    }

    public String getUntypedPortion() {
        return currentParagraph.getUntypedPortion();
    }

    public boolean isInGame() {
        return inGame;
    }

    public boolean isEnded() {
        return gameOver;
    }

    public int getTimer() {
        return timer;
    }

    public int getPosition() {
        return currentParagraph.getIndex();
    }

}
