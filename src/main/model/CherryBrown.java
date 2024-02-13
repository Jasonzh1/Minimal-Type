package model;

public class CherryBrown extends Item {

    // EFFECTS: Initializes Cherry Brown item with name and description
    public CherryBrown() {
        super("Cherry MX Brown", "Removes all periods from the text.");
    }

    // REQUIRES: Game that just entered 'inGame' with no calls to tick()
    // MODIFIES: game
    // EFFECTS: Removes all periods from currentParagraph in game
    @Override
    public boolean perfromAbility(Game game) {
        return game.currentParagraph.removePeriods();
    }
}
