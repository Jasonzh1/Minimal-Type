package ui;

import java.awt.*;

import javax.swing.*;

import model.Game;

// Renders game environment
public class GameRender extends JPanel {
    private final Game game;
    private static final Font font = new Font("Gill Sans MT", Font.PLAIN, 20);

    private boolean cursor;

    // Creates new game environment
    public GameRender(Game game) {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(scrn);
        setBackground(Color.BLACK);

        this.game = game;
        this.cursor = false;
    }

    // Taken from B02-Spaceinvaders
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        renderGame(g);
    }

    // EFFECTS: Renders typing game state
    private void renderGame(Graphics g) {
        g.setFont(font);
        g.setColor(new Color(0, 255, 215));
        g.drawString("Timer " + game.getTimer(), 100, 150);


        String typed = game.getTypedPortion();
        String untyped = game.getUntypedPortion();
        int wordStart = 100 - (int) g.getFontMetrics().getStringBounds(typed, g).getWidth();

        g.setColor(new Color(0, 255, 215));
        g.drawString(typed, wordStart, 400);

        g.setColor(new Color(255, 255, 255));
        g.drawString(untyped, 100, 400);
        g.drawString("- " + game.getAuthor(), 150, 500);

        if (cursor) {
            g.fillRect(100, 405, 8, 2);
        }
    }

    // MODIFIES: this
    // EFFECTS: Changes cursor state
    public void blink() {
        cursor = !cursor;
    }
}
