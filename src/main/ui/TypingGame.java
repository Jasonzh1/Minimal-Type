package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Game;
import model.tools.Inventory;

import java.io.IOException;

// Main window where game is played
public class TypingGame extends JFrame {
    private static final int INTERVAL = 10;

    private final GameRender gameRender;
    private final Menu menu;
    private final CardLayout cardLayout;
    private final Container cardPane;

    private final Game game;
    private final Inventory inventory;


    public TypingGame() throws IOException {
        super("True Type");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);

        this.game = new Game();
        this.inventory = new Inventory();
        this.gameRender = new GameRender(game);
        this.menu = new Menu(game, inventory);

        this.cardLayout = new CardLayout();
        this.cardPane = getContentPane();
        cardPane.setLayout(cardLayout);

        cardPane.add("game", gameRender);
        cardPane.add("menu", menu);

        cardLayout.show(cardPane, "menu");

        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
    }

    // Set up timer
    // modifies: none
    // effects:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, ae -> {
            if (game.isInGame()) {
                game.tick();
                cardLayout.show(cardPane, "game");
                gameRender.repaint();
            } else {
                cardLayout.show(cardPane, "menu");
                menu.repaint();
            }
        });

        t.start();
    }

    // Taken from B02-SpaceInvaders
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }


    // Taken from B02-SpaceInvaders
    // A key handler to respond to key events
    private class KeyHandler extends KeyAdapter {
        // MODIFIES: game
        // EFFECTS: A key handler to respond to key events
        @Override
        public void keyPressed(KeyEvent e) {
            char c = e.getKeyChar();
            if (game.isInGame()) {
                game.handleInput(c);
            } else {
                menu.handleInput(c);
            }
        }
    }
}
