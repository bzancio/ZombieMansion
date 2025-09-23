package actions;

import game.Game;

import java.util.ArrayList;
import java.util.List;

public class EscapeAction implements ActionStrategy {

    public static boolean isAvailable(Game game) {
        return !game.getCurrentRoom().hasActiveZombies() && (game.getCurrentRoomNumber() == game.getDifficulty().getRoomNumber());
    }

    @Override
    public List<ActionResult> execute() {
        List<ActionResult> results = new ArrayList<>();
        results.add(new ActionResult(ActionResult.ActionResultType.ESCAPED, null, -1, -1));
        return results;
    }
}
