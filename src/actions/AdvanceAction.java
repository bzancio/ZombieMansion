package actions;

import game.Game;
import results.ActionResult;
import results.AdvancedRoomResult;

import java.util.ArrayList;
import java.util.List;

public record AdvanceAction(Game game) implements ActionStrategy {

    public static boolean isAvailable(Game game) {
        return !game.getCurrentRoom().hasActiveZombies() && (game.getCurrentRoomNumber() != game.getDifficulty().getRoomNumber());
    }

    @Override
    public List<ActionResult> execute() {
        List<ActionResult> results = new ArrayList<>();
        game.advanceRoom();
        results.add(new AdvancedRoomResult(game.getCurrentRoomNumber()));
        return results;
    }
}
