package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Event;
import model.EventLog;
import model.Game;
import model.tools.Inventory;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

// Main window where game is played
public class TypingGame extends JFrame implements WindowListener {
    private static final int INTERVAL = 10;
    private int blinker;

    private final GameRender gameRender;
    private final Menu menu;
    private final CardLayout cardLayout;
    private final Container cardPane;

    private final Game game;
    private final Inventory inventory;
    private final StartScreen screen;


    // Creates new instance of TypingGame
    public TypingGame() throws IOException {
        super("Minimal Type");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);

        this.game = new Game();
        this.inventory = new Inventory();
        this.gameRender = new GameRender(game);
        this.menu = new Menu(game, inventory);
        this.blinker = 0;
        this.screen = new StartScreen();

        this.cardLayout = new CardLayout();
        this.cardPane = getContentPane();
        cardPane.setLayout(cardLayout);

        cardPane.add("game", gameRender);
        cardPane.add("menu", menu);
        cardPane.add("screen", screen);

        cardLayout.show(cardPane, "menu");

        addKeyListener(new KeyHandler());
        addWindowListener(this);
        setFocusable(true);
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
            if (!screen.isFinished()) {
                blinker++;
                if (blinker % 30 == 0) {
                    screen.blink();
                }
                cardLayout.show(cardPane, "screen");
                screen.repaint();
            } else if (game.isInGame()) {
                blinker++;
                if (blinker % 30 == 0) {
                    gameRender.blink();
                }
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

    // effects: no effect
    @Override
    public void windowOpened(WindowEvent e) {
        // Ignore
    }

    // effects: Prints out EventLog on window closing
    @Override
    public void windowClosing(WindowEvent e) {
        EventLog events = EventLog.getInstance();
        for (Event event : events) {
            System.out.println(event.toString());
        }
    }

    // effects: no effect
    @Override
    public void windowClosed(WindowEvent e) {
        // Ignore
    }

    // effects: no effect
    @Override
    public void windowIconified(WindowEvent e) {
        // Ignore
    }

    // effects: no effect
    @Override
    public void windowDeiconified(WindowEvent e) {
        // Ignore
    }

    // effects: no effect
    @Override
    public void windowActivated(WindowEvent e) {
        // Ignore
    }

    // effects: no effect
    @Override
    public void windowDeactivated(WindowEvent e) {
        // Ignore
    }


    // Taken from B02-SpaceInvaders
    // EFFECTS: A key handler to respond to key events
    private class KeyHandler extends KeyAdapter {
        // MODIFIES: game
        // EFFECTS: A key handler to respond to key events
        @Override
        public void keyPressed(KeyEvent e) {
            char c = e.getKeyChar();
            if (!screen.isFinished()) {
                screen.typeCharacter(c);
            } else if (game.isInGame()) {
                game.handleInput(c);
            }
        }
    }
}
