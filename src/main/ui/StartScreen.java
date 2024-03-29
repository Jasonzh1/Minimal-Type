package ui;

import model.tools.Paragraph;
import model.tools.TypeCharacter;

import javax.swing.*;
import java.awt.*;

// Represents initial loading screen
public class StartScreen extends JPanel {
    private ImageIcon img;

    private static final Font font = new Font("Gill Sans MT", Font.PLAIN, 30);
    private boolean cursor;
    private TypeCharacter typer;
    private Paragraph paragraph;

    // EFFECTS: Creates new loading screen
    public StartScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(scrn);
        setBackground(Color.black);

        paragraph = new Paragraph("Type to begin", "");
        typer = new TypeCharacter();
        typer.setCurrentParagraph(paragraph);

        img = new ImageIcon("data/MINIMAL TYPE.jpg");
        add(new JLabel(img));
        this.cursor = false;
    }

    // Taken from B02-Spaceinvaders
    // EFFECTS: Renders loading screen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        renderGame(g);
    }

    // EFFECTS: Renders typing game state
    private void renderGame(Graphics g) {
        String typed = paragraph.getTypedPortion();
        String untyped = paragraph.getUntypedPortion();
        g.setFont(font);

        int wordStart = 70 + (int) g.getFontMetrics().getStringBounds(typed, g).getWidth();

        g.setColor(new Color(0, 255, 215));
        g.drawString(typed, 70, 725);

        g.setColor(new Color(255, 255, 255));
        g.drawString(untyped, wordStart, 725);

        if (cursor) {
            g.fillRect(wordStart, 730, 15, 3);
        }
    }

    // MODIFIES: this
    // EFFECTS: Changes cursor state
    public void blink() {
        cursor = !cursor;
    }

    // MODIFIES: typer
    // EFFECTS: Inputs character into typer
    public void typeCharacter(char c) {
        typer.handleInput(c);
    }

    // EFFECTS: Returns whether to exit loading screen
    public boolean isFinished() {
        return paragraph.isFinished();
    }
}
