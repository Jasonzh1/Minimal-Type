package model;


public class CherryRed extends Item {

    // EFFECTS: Initializes Cherry Red item with name and description
    public CherryRed() {
        super("Cherry MX Red", "Begin game with 15 characters already typed.");
    }

    // REQUIRES: Game that just entered 'inGame' with no calls to tick()
    // MODIFIES: game
    // EFFECTS: Increases the position of paragraph by 15
    @Override
    public void perfromAbility(Game game) {
        for (int i = 0; i < 15; i++) {
            game.currentParagraph.typeCharacter();
        }
    }
}
