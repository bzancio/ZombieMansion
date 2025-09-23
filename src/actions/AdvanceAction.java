package actions;

import game.Game;

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
        results.add(new ActionResult(ActionResult.ActionResultType.ADVANCED_ROOM, null, game().getCurrentRoomNumber(), -1));
        return results;
    }
}
