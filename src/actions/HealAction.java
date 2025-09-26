package actions;

import game.Game;
import game.Player;
import results.ActionResult;
import results.PlayerHealsResult;

import java.util.ArrayList;
import java.util.List;

public record HealAction(Player player) implements ActionStrategy {

    public static boolean isAvailable(Game game) {
        return !game.getCurrentRoom().hasActiveZombies() && game.getPlayer().getHasKit();
    }

    @Override
    public List<ActionResult> execute() {
        List<ActionResult> results = new ArrayList<>();
        player.useKit();
        results.add(new PlayerHealsResult());
        return results;
    }
}