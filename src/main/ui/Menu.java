package ui;

import model.Item;
import model.tools.Inventory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.Game;

public class Menu extends JPanel {
    private static final String[] JSON_STORE = {"./data/save1.json", "./data/save1.json", "./data/save1.json"};
    private static final Font font = new Font("Gill Sans MT", Font.PLAIN, 20);
    private static final Color white = new Color(255, 255, 255);
    private final JsonReader jsonReader;
    private final JsonWriter jsonWriter;

    private boolean inInventory;
    private boolean inLoad;
    private boolean inSave;

    private final Game game;
    private Inventory inventory;
    private Item selectedItem;
    private String message;

    public Menu(Game game, Inventory inventory) {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(scrn);
        setBackground(Color.BLACK);

        this.inventory = inventory;
        this.game = game;

        this.selectedItem = null;
        this.message = "";

        this.inInventory = false;
        this.inLoad = false;
        this.inSave = false;

        this.jsonReader = new JsonReader(JSON_STORE);
        this.jsonWriter = new JsonWriter(JSON_STORE);
    }

    public void handleInput(char c) {
        if (inInventory) {
            handleInventoryInput(c);
        } else if (inLoad) {
            handleLoadInput(c);
        } else if (inSave) {
            handleSaveInput(c);
        } else {
            handleMenuInput(c);
        }
    }

    public void handleMenuInput(char c) {
        int select = c - '0';
        if (select == 6) {
            inInventory = true;
            message = "";
        } else if (select == 7) {
            inLoad = true;
            message = "";
        } else if (select == 8) {
            inSave = true;
            message = "";
        } else if (select < 6 && select > 0) {
            game.initializeGame(select);
            if (selectedItem != null) {
                selectedItem.perfromAbility(game);
                selectedItem = null;
            }
        }
    }

    // EFFECTS: Polls inventory state for user input
    private void handleInventoryInput(char c) {
        int select = c - '0';
        if (select < inventory.length() + 1 && select > 0) {
            selectedItem = inventory.useItem(select - 1);
            inInventory = false;
        } else if (select == inventory.length() + 1) {
            inInventory = false;
        }
        if (selectedItem != null) {
            message = selectedItem.getName() + " successfully used.";
        }
    }

    // EFFECTS: Polls load state for user input
    private void handleLoadInput(char c) {
        int select = c - '0';
        if (select < 4 && select > 0) {
            if (loadSave(select - 1)) {
                message = "Successfully loaded save file # " + select;
            }
            inLoad = false;
        } else if (select == 4) {
            inLoad = false;
        }
    }

    // EFFECTS: Polls save state for user input
    private void handleSaveInput(char c) {
        int select = c - '0';
        if (select < 4 && select > 0) {
            if (saveData(select - 1)) {
                message = "Successfully saved to file # " + select;
            }
            inSave = false;
        } else if (select == 4) {
            inSave = false;
        }
    }

    // EFFECTS: Load save file
    private boolean loadSave(int select) {
        try {
            inventory = jsonReader.read(select);
            return inventory != null;
        } catch (IOException e) {
            return false;
        }
    }

    // EFFECTS: Saves data
    private boolean saveData(int select) {
        try {
            jsonWriter.open(select);
            jsonWriter.write(inventory);
            jsonWriter.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    // Taken from B02-Spaceinvaders
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(font);
        g.setColor(white);

        if (inInventory) {
            renderInventory(g);
        } else if (inLoad || inSave) {
            renderLoad(g);
        } else {
            renderMenu(g);
        }
    }

    // EFFECTS: Renders load environment
    private void renderLoad(Graphics g) {
        if (inLoad) {
            g.drawString("Select Save File:", 100, 250);
        } else if (inSave) {
            g.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
            g.drawString("Careful! Any save data will be overwritten.", 100, 200);
            g.setFont(font);
            g.drawString("Select Save File:", 100, 250);
        }
        g.drawString("1. Save #1", 100, 300);
        g.drawString("2. Save #2", 100, 350);
        g.drawString("3. Save #3", 100, 400);

        g.drawString("4. Exit to Menu", 100, 475);
    }

    // EFFECTS: Renders inventory environment
    private void renderInventory(Graphics g) {
        g.drawString("Current Items:", 100, 250);
        Item currentItem;
        for (int i = 1; i <= inventory.length(); i++) {
            currentItem = inventory.getItem(i - 1);
            String itemTitle = i + ". " + currentItem.getName() + ": " + currentItem.getDescription();
            g.drawString(itemTitle, 100, 250 + (i * 50));
        }
        int num = inventory.length() + 1;
        g.drawString(num + ". Exit Inventory", 100, 250 + (num * 50));
    }

    // EFFECTS: Renders menu state
    private void renderMenu(Graphics g) {
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
        g.drawString(message, 100, 150);

        g.drawString("Choose difficulty level (Words-Per-Minute): ", 100, 250);
        g.drawString("1. 15   2. 30   3. 50   4. 80   5. 100", 100,400);
        g.drawString("6. Inventory   7. Load Save   8. Save Game", 100, 450);

    }
}
