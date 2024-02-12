package ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.Game;

import java.io.IOException;

public class TerminalGame {
    private Game game;
    private Screen screen;

    // MODIFIES: this
    // EFFECTS:  initializes the terminal and instantiates a new game
    public void start() throws IOException, InterruptedException {
        screen = new DefaultTerminalFactory()
                .setPreferTerminalEmulator(false)
                .setInitialTerminalSize(new TerminalSize(100, 40))
                .createScreen();

        screen.startScreen();
        screen.doResizeIfNecessary();
        screen.setCursorPosition(null);

        game = new Game();

        beginTicks();
    }

    // EFFECTS: Runs tick Game.TICKS_PER_SECOND times per second until game is ended
    private void beginTicks() throws IOException, InterruptedException {
        while (!game.isEnded()) {
            tick();
            Thread.sleep(1000L / Game.TICKS_PER_SECOND);
        }

        System.exit(0);
    }

    // EFFECTS: Refreshes game state every Game.TICKS_PER_SECOND
    private void tick() throws IOException {
        handleUserInput();

        game.tick();

        screen.clear();
        render();
        screen.refresh();
    }

    // EFFECTS: Polls environment for user input and handles accordingly
    private void handleUserInput() throws IOException {
        KeyStroke stroke = screen.pollInput();

        if (stroke == null || stroke.getCharacter() == null) {
            return;
        }

        char c = stroke.getCharacter();
        if (game.isInGame()) {
            game.handleInput(c);
        } else {
            int select = c - '0';
            if (select > 6 || select < 1) {
                return;
            }
            game.initializeGame(select);
        }
    }

    // EFFECTS: Renders game environment
    private void render() {
        if (game.isInGame()) {
            renderGame();
        } else {
            renderMenu();
        }
    }

    // EFFECTS: Renders typing game state
    private void renderGame() {
        drawString("Timer " + game.getTimer(), TextColor.ANSI.CYAN, 5, 5);
        String typed = game.getTypedPortion();
        String untyped = game.getUntypedPortion();
        int wordStart = game.getPosition() * -1 + 10;
        int untypedStart = wordStart + typed.length();

        drawString(typed, TextColor.ANSI.CYAN, wordStart, 20);
        drawString(untyped, TextColor.ANSI.WHITE, untypedStart, 20);
    }

    // EFFECTS: Renders menu state
    private void renderMenu() {
        drawString("Choose difficulty level (Words-Per-Minute): ", TextColor.ANSI.WHITE, 10, 15);
        drawString("1. 15   2. 30   3. 50   4. 80   5. 100   6. Exit Game", TextColor.ANSI.WHITE, 10, 20);
    }

    // Taken from Lab #4
    // EFFECTS: Calls drawCharacter with each individual character
    private void drawString(String s, TextColor c, int startX, int y) {
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            drawCharacter(chars[i], c, startX + i, y);
        }
    }

    // Taken from Lab #4
    // EFFECTS: Uses Lanterna to render each character with a specific colour
    private void drawCharacter(char c, TextColor color, int x, int y) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(color);
        text.putString(x, y + 1, String.valueOf(c));
    }
}
