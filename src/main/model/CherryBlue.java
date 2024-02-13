package model;

public class CherryBlue extends Item {

    // EFFECTS: Initializes Cherry Blue item with name and description
    public CherryBlue() {
        super("Cherry MX Blue", "Gives 10 seconds of extra time.");
    }

    // REQUIRES: Game that just entered 'inGame' with no calls to tick()
    // MODIFIES: game
    // EFFECTS: Increases the timer by 10
    @Override
    public void perfromAbility(Game game) {
        game.timer += 10;
    }
}
