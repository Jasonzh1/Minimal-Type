package ui;

import model.Item;
import model.tools.Inventory;
import model.tools.Paragraph;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import model.Game;

// JPanel that represents the menu screen
public class Menu extends JPanel implements ActionListener {
    private static final String[] JSON_STORE = {"./data/save1.json", "./data/save1.json", "./data/save1.json"};
    private static final Font font = new Font("Gill Sans MT", Font.PLAIN, 20);
    private static final Color white = new Color(255, 255, 255);
    private static final Color aqua = new Color(0, 255, 215);

    private final JsonReader jsonReader;
    private final JsonWriter jsonWriter;

    private boolean inInventory;
    private boolean inLoad;
    private boolean inSave;

    private final Game game;
    private Inventory inventory;
    private Item selectedItem;
    private String message;

    ArrayList<JButton> buttons = new ArrayList<>();
    JButton currentButton;

    JButton add;
    JButton remove;

    // EFFECTS: Creates new menu
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

        setLayout(null);
        setupButtons();
        setupInventoryButtons();
        setMenuButtons();
        addHover();
    }

    // MODIFIES: this
    // EFFECTS: Creates 8 Jbuttons
    private void setupButtons() {
        for (int i = 0; i < 8; i++) {
            buttons.add(new JButton());
        }

        for (int i = 0; i < 8; i++) {
            buttons.get(i).addActionListener(this);
            buttons.get(i).setOpaque(false);
            buttons.get(i).setContentAreaFilled(false);
            buttons.get(i).setBorderPainted(false);
            add(buttons.get(i));
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates buttons for inventory config
    private void setupInventoryButtons() {
        add = new JButton();
        remove = new JButton();
        Icon addIcon = new ImageIcon("data/icons8-add-30.png");
        Icon removeIcon = new ImageIcon("data/icons8-remove-30.png");

        add.addActionListener(this);
        add.setBorder(null);
        add.setContentAreaFilled(false);
        add.setBorderPainted(false);
        add.setMargin(new Insets(0, 0, 0, 0));
        add.setIcon(addIcon);
        add(add);

        remove.addActionListener(this);
        remove.setBorder(null);
        remove.setContentAreaFilled(false);
        remove.setBorderPainted(false);
        remove.setMargin(new Insets(0, 0, 0, 0));
        remove.setIcon(removeIcon);
        add(remove);

        add.setVisible(false);
        remove.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: Sets buttons for menu config
    private void setMenuButtons() {
        buttons.get(0).setBounds(95, 375, 55, 40);
        buttons.get(1).setBounds(150, 375, 55, 40);
        buttons.get(2).setBounds(205, 375, 55, 40);
        buttons.get(3).setBounds(260, 375, 60, 40);
        buttons.get(4).setBounds(320, 375, 70, 40);
        buttons.get(5).setBounds(80, 425, 115, 40);
        buttons.get(6).setBounds(205, 425, 115, 40);
        buttons.get(7).setBounds(328, 425, 120, 40);
        for (int i = 0; i < 8; i++) {
            buttons.get(i).setVisible(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets up buttons for inventory config
    private void setInventoryButtons() {
        for (int i = 1; i <= inventory.length(); i++) {
            buttons.get(i - 1).setBounds(90, 225 + (i * 50), 500, 35);
        }
        int temp = inventory.length();
        buttons.get(temp).setBounds(90, 225 + ((temp + 1) * 50), 150, 35);
        for (int i = temp + 1; i < 8; i++) {
            buttons.get(i).setVisible(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets up buttons for load config
    private void setLoadButtons() {
        buttons.get(0).setBounds(90, 275, 85, 35);
        buttons.get(1).setBounds(90, 325, 85, 35);
        buttons.get(2).setBounds(90, 375, 85, 35);
        buttons.get(3).setBounds(90, 450, 125, 40);

        for (int i = 4; i < 8; i++) {
            buttons.get(i).setVisible(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets up button hover effect
    private void addHover() {
        for (int i = 0; i < 8; i++) {
            int finalI = i;
            buttons.get(i).getModel().addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if (buttons.get(finalI).getModel().isRollover()) {
                        currentButton = buttons.get(finalI);
                    } else {
                        currentButton = null;
                    }
                }
            });
        }
    }

    // MODIFIES: this
    // EFFECTS: Enables inventory selection icons
    private void enableIcons(int index) {
        add.setBounds(650, 280 + 50 * index, 30, 30);
        remove.setBounds(700, 280 + 50 * index, 30, 30);
        add.setVisible(true);
        remove.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Disables inventory selection icons
    private void disableIcons() {
        add.setVisible(false);
        remove.setVisible(false);
    }

    // EFFECTS: Handles user input
    public void handleInput(char c) {
        if (inLoad) {
            handleLoadInput(c);
        } else if (inSave) {
            handleSaveInput(c);
        } else {
            handleMenuInput(c);
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles input in menu state
    public void handleMenuInput(char c) {
        int select = c - '0';
        if (select == 6) {
            inInventory = true;
            message = "";
            setInventoryButtons();
        } else if (select == 7) {
            inLoad = true;
            message = "";
            setLoadButtons();
        } else if (select == 8) {
            inSave = true;
            message = "";
            setLoadButtons();
        } else if (select < 6 && select > 0) {
            game.initializeGame(select);
            if (selectedItem != null) {
                selectedItem.perfromAbility(game);
                selectedItem = null;
            }
        }
    }

    // EFFECTS: Polls inventory state for user input
    private void handleInventoryInput(int select) {
        if (select < inventory.length() + 1 && select > 0) {
            selectedItem = inventory.useItem(select - 1);
            inInventory = false;
            setMenuButtons();
            disableIcons();
        } else if (select == inventory.length() + 1) {
            inInventory = false;
            setMenuButtons();
            disableIcons();
        } else if (select <= 0) {
            inventory.removeItem(-1 * select);
            disableIcons();
            setInventoryButtons();
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
            setMenuButtons();
        } else if (select == 4) {
            inLoad = false;
            setMenuButtons();
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
            setMenuButtons();
        } else if (select == 4) {
            inSave = false;
            setMenuButtons();
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
    // EFFECTS: Renders menu
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
        drawSaveButtons(g);
    }

    // EFFECTS: Renders save environment
    private void drawSaveButtons(Graphics g) {
        if (currentButton == buttons.get(0)) {
            g.setColor(aqua);
        }
        g.drawString("Save #1", 100, 300);
        resetFontColor(g);
        if (currentButton == buttons.get(1)) {
            g.setColor(aqua);
        }
        g.drawString("Save #2", 100, 350);
        resetFontColor(g);
        if (currentButton == buttons.get(2)) {
            g.setColor(aqua);
        }
        g.drawString("Save #3", 100, 400);
        resetFontColor(g);

        if (currentButton == buttons.get(3)) {
            g.setColor(aqua);
        }
        g.drawString("Exit to Menu", 100, 475);
        resetFontColor(g);
    }

    // EFFECTS: Renders inventory environment
    private void renderInventory(Graphics g) {
        g.drawString("Current Items:", 100, 250);
        Item currentItem;
        for (int i = 1; i <= inventory.length(); i++) {
            currentItem = inventory.getItem(i - 1);
            String itemTitle = i + ". " + currentItem.getName() + ": " + currentItem.getDescription();

            if (currentButton == buttons.get(i - 1)) {
                g.setColor(aqua);
            }
            g.drawString(itemTitle, 100, 250 + (i * 50));
            resetFontColor(g);
        }
        int num = inventory.length() + 1;
        if (currentButton == buttons.get(num - 1)) {
            g.setColor(aqua);
        }
        g.drawString("Exit Inventory", 100, 250 + (num * 50));
        resetFontColor(g);
    }

    // EFFECTS: Renders menu state
    private void renderMenu(Graphics g) {
        if (game.getTimer() == 0 && !game.isInGame()) {
            message = "Good Try! You'll get it next time!";
        } else if (game.getTimer() > 0 && !game.isInGame()) {
            if (inventory.addRandomItem()) {
                message = "Congratulations on completing the level! You got a new item!";
            } else {
                message = "Congratulations on completing the level!";
            }
            game.resetTimer();
        }
        g.drawString(message, 100, 150);

        g.drawString("Choose difficulty level (Words-Per-Minute): ", 100, 250);
        drawButtons(g);
        drawButtons2(g);
    }

    // EFFECTS: Renders menu buttons
    private void drawButtons(Graphics g) {
        for (int i = 0; i < 5; i++) {
            JButton b = buttons.get(i);
            if (currentButton == b) {
                g.setColor(new Color(0, 255, 215));
            } else {
                g.setColor(white);
            }
            g.drawString(String.valueOf((i + 1) * 15), 100 + i * 60, 400);
        }
        g.setColor(white);
    }

    // EFFECTS: Renders inventory buttons
    private void drawButtons2(Graphics g) {
        if (currentButton == buttons.get(5)) {
            g.setColor(new Color(0, 255, 215));
        }
        g.drawString("Inventory", 100, 450);
        resetFontColor(g);
        if (currentButton == buttons.get(6)) {
            g.setColor(new Color(0, 255, 215));
        }
        g.drawString("Load Save", 220, 450);
        resetFontColor(g);
        if (currentButton == buttons.get(7)) {
            g.setColor(new Color(0, 255, 215));
        }
        g.drawString("Save Game", 340, 450);
        resetFontColor(g);
    }

    // EFFECTS: Performs action from button press
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inInventory) {
            inventoryAction(e);
            return;
        }
        if (e.getSource() == buttons.get(0)) {
            handleInput('1');
        } else if (e.getSource() == buttons.get(1)) {
            handleInput('2');
        } else if (e.getSource() == buttons.get(2)) {
            handleInput('3');
        } else if (e.getSource() == buttons.get(3)) {
            handleInput('4');
        } else if (e.getSource() == buttons.get(4)) {
            handleInput('5');
        } else if (e.getSource() == buttons.get(5)) {
            handleInput('6');
        } else if (e.getSource() == buttons.get(6)) {
            handleInput('7');
        } else if (e.getSource() == buttons.get(7)) {
            handleInput('8');
        }
    }

    // EFFECTS: Performs inventory action from button press
    private void inventoryAction(ActionEvent e) {
        JButton actionable = (JButton) e.getSource();
        if (actionable == buttons.get(inventory.length())) {
            handleInventoryInput(inventory.length() + 1);
            return;
        } else if (actionable == add) {
            handleInventoryInput((add.getY() - 280) / 50 + 1);
        } else if (actionable == remove) {
            System.out.println(((remove.getY() - 280) / 50) * -1);
            handleInventoryInput(((remove.getY() - 280) / 50) * -1);
        } else {
            enableIcons(buttons.indexOf(currentButton));
        }
    }

    // MODIFIES: this
    // EFFECTS: Resets graphics font color
    private void resetFontColor(Graphics g) {
        g.setColor(white);
    }
}
