package model;
import model.tools.*;

import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CherryBrownTest {
    Item cherryBrown;

    @BeforeEach
    void setCherryBlue() {
        cherryBrown = new CherryBrown();
    }

    @Test
    void testCherryBlue() {
        assertEquals("Cherry MX Brown", cherryBrown.getName());
        assertEquals("Removes all periods from the text.", cherryBrown.getDescription());
    }


    @Test
    void performAbilityTest() throws IOException {
        Game game = new Game();
        game.initializeGame(1);
        Paragraph para = game.getCurrentParagraph();
        para.removePeriods();
        assertEquals(para, game.getCurrentParagraph());

        assertTrue(cherryBrown.perfromAbility(game));

        assertEquals(para.getUntypedPortion(), game.getUntypedPortion());
    }
}
