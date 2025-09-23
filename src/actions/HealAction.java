package actions;

import game.Game;
import game.Player;

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
        results.add(new ActionResult(ActionResult.ActionResultType.PLAYER_HEALS, null, -1, -1));
        return results;
    }
}