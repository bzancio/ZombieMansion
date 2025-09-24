package actions;

import game.Game;
import game.Player;
import game.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SearchAction implements ActionStrategy {
    private final Game game;
    private final Room room;
    private final Player player;

    public SearchAction(Game game) {
        this.game = game;
        this.room = game.getCurrentRoom();
        this.player = game.getPlayer();
    }

    public static boolean isAvailable(Game game) {
        return !game.getCurrentRoom().hasActiveZombies() && game.getCurrentRoom().getRemainingSearchAttemps() != 0;
    }

    @Override
    public List<ActionResult> execute() {
        room.setRemainingSearchAttemps(room.getRemainingSearchAttemps() - 1);
        List<ActionResult> results = new ArrayList<>();
        results.add(new ActionResult(ActionResult.ActionResultType.PLAYER_SEARCHED, null, -1, -1));
        double mainRoll = ThreadLocalRandom.current().nextDouble(0, 100);

        if (mainRoll <= 75) {
            results.add(new ActionResult(ActionResult.ActionResultType.SEARCH_NOISE, null, -1, -1));
            results.add(noiseResult());
        } else if (mainRoll <= 90) {
            results.add(new ActionResult(ActionResult.ActionResultType.KIT_FOUND, null, -1, -1));
            if (player.getHasKit()) {
                results.add(new ActionResult(ActionResult.ActionResultType.KIT_FULL, null, -1 , -1));
            } else
                player.setHasKit(true);
        } else if (mainRoll <= 95) {
            player.setNumberProtections(player.getNumberProtections() + 1);
            results.add(new ActionResult(ActionResult.ActionResultType.PROTECTION_FOUND, null, -1 , -1));
        } else {
            player.setNumberWeapons(player.getNumberWeapons() + 1);
            results.add(new ActionResult(ActionResult.ActionResultType.WEAPON_FOUND, null, -1, -1));
        }
        return results;
    }

    private ActionResult noiseResult() {
        double noiseRoll = ThreadLocalRandom.current().nextDouble(0, 100);
        if (noiseRoll <= 40)
            return new ActionResult(ActionResult.ActionResultType.NOISE_IGNORED, null, -1, -1);
        room.addZombie(game.getCurrentRoomNumber());
        if (noiseRoll <= 80) {
            room.setActiveZombies(room.getActiveZombies() + 1);
            return new ActionResult(ActionResult.ActionResultType.ZOMBIE_APPEARED, null, 1, -1);
        } else {
            room.addZombie(game.getCurrentRoomNumber());
            room.setActiveZombies(room.getActiveZombies() + 2);
            return new ActionResult(ActionResult.ActionResultType.ZOMBIE_APPEARED, null, 2, -1);
        }
    }
}