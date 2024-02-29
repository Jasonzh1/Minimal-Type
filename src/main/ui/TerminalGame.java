package ui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.Game;
import model.Item;
import model.tools.Inventory;

import java.io.IOException;

public class TerminalGame {
    private Game game;
    private Screen screen;
    private Inventory inventory;
    private boolean inInventory;
    private Item selectedItem;
    private String message;

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

        this.game = new Game();
        this.inventory = new Inventory();
        this.inInventory = false;
        this.selectedItem = null;
        this.message = "";

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
        if (!inInventory) {
            handleUserInput();
        } else {
            handleInventoryInput();
        }

        if (game.isInGame()) {
            game.tick();
        }

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
            return;
        }

        int select = c - '0';

        if (select == 6) {
            inInventory = true;
        } else if (select > 7 || select < 1) {
            return;
        } else {
            game.initializeGame(select);
            if (selectedItem != null) {
                selectedItem.perfromAbility(game);
                selectedItem = null;
            }
        }
    }

    // EFFECTS: Polls inventory state for user input
    private void handleInventoryInput() throws IOException {
        KeyStroke stroke = screen.pollInput();

        if (stroke == null || stroke.getCharacter() == null) {
            return;
        }

        int select = stroke.getCharacter() - '0';
        if (select > inventory.length() + 1 || select < 1) {
            return;
        } else if (select == inventory.length() + 1) {
            inInventory = false;
            return;
        } else {
            selectedItem = inventory.useItem(select - 1);
            inInventory = false;
        }
    }

    // EFFECTS: Renders game environment
    private void render() {
        if (game.isInGame()) {
            renderGame();
        } else if (inInventory) {
            renderInventory();
        } else {
            renderMenu();
        }
    }

    private void renderInventory() {
        drawString("Current Items:", TextColor.ANSI.WHITE, 10, 10);
        Item currentItem;
        for (int i = 1; i <= inventory.length(); i++) {
            currentItem = inventory.getItem(i - 1);
            String itemTitle = i + ". " + currentItem.getName() + ": " + currentItem.getDescription();
            drawString(itemTitle, TextColor.ANSI.WHITE, 10, 10 + (i * 2));
        }
        int num = inventory.length() + 1;
        drawString(num + ". Exit Inventory", TextColor.ANSI.WHITE, 10, 10 + (num * 2));
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
        drawString("- " + game.getAuthor(), TextColor.ANSI.WHITE, 10, 30);
    }

    // EFFECTS: Renders menu state
    private void renderMenu() {
        if (game.getTimer() == 0) {
            message = "Good Try! You'll get it next time!";
        } else if (game.getTimer() > 0) {
            if (inventory.addRandomItem()) {
                message = "Congratulations on completing the level! You got a new item!";
            } else {
                message = "Congratulations on completing the level!";
            }
            game.resetTimer();
        }
        drawString(message, TextColor.ANSI.WHITE, 10, 10);

        drawString("Choose difficulty level (Words-Per-Minute): ", TextColor.ANSI.WHITE, 10, 15);
        drawString("1. 15   2. 30   3. 50   4. 80   5. 100   6. Inventory   7. Exit Game",
                TextColor.ANSI.WHITE,
                10,
                20);
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
