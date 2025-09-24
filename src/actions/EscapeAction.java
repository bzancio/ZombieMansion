package actions;

import game.Game;
import results.ActionResult;
import results.EscapedResult;

import java.util.ArrayList;
import java.util.List;

public class EscapeAction implements ActionStrategy {

    public static boolean isAvailable(Game game) {
        return !game.getCurrentRoom().hasActiveZombies() && (game.getCurrentRoomNumber() == game.getDifficulty().getRoomNumber());
    }

    @Override
    public List<ActionResult> execute() {
        List<ActionResult> results = new ArrayList<>();
        results.add(new EscapedResult());
        return results;
    }
}
