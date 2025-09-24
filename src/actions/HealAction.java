package actions;

import game.Game;
import game.Player;
import results.ActionResult;
import results.PlayerHealsResult;

import java.util.ArrayList;
import java.util.List;

public class HealAction implements ActionStrategy {
    Player player;

    public HealAction(Game game) {
        this.player = game.getPlayer();
    }

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