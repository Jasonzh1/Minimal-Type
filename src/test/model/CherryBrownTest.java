package model;
import model.tools.*;

import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CherryBrownTest {
    CherryBrown cherryBrown;
    Game testGame;
    Game testGame2;

    @BeforeEach
    void setCherryBlue() throws IOException{
        cherryBrown = new CherryBrown();
        testGame = new Game();
        testGame2 = new Game();
        testGame.initializeGame(1);
        testGame2.initializeGame(3);
    }

    @Test
    void testCherryBlue() {
        assertEquals("Cherry MX Brown", cherryBrown.getName());
        assertEquals("Removes all periods from the text.", cherryBrown.getDescription());
    }


    @Test
    void performAbilityTest() {
        Paragraph para1 = testGame.getCurrentParagraph();
        Paragraph para2 = testGame2.getCurrentParagraph();
        para1.removePeriods();
        para2.removePeriods();

        cherryBrown.perfromAbility(testGame);
        cherryBrown.perfromAbility(testGame2);

        assertEquals(para1.getParagraph(), testGame.getUntypedPortion());
        assertEquals(para2.getParagraph(), testGame2.getUntypedPortion());
    }
}
