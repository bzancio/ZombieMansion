package actions;

import game.Game;
import game.Player;
import game.Room;
import game.SearchResult;

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
        int roll = ThreadLocalRandom.current().nextInt(0, 101);

        switch (roll) {
            case 1-75 :
                results.add(new ActionResult(ActionResult.ActionResultType.SEARCH_NOISE, null, -1, -1));
                results.add(noiseResult());
                break;
            case 76-90 :
                results.add(new ActionResult(ActionResult.ActionResultType.KIT_FOUND, null, -1, -1));
                if (!player.getHasKit()) {
                    player.setHasKit(true);
                }
                else
                    results.add(new ActionResult(ActionResult.ActionResultType.KIT_FULL, null, -1 , -1));
                break;
            case 91-95:
                player.setNumberProtections(player.getNumberProtections() + 1);
                results.add(new ActionResult(ActionResult.ActionResultType.PROTECTION_FOUND, null, -1 , -1));
                break;
            case 96-99 :
                player.setNumberWeapons(player.getNumberWeapons() + 1);
                results.add(new ActionResult(ActionResult.ActionResultType.WEAPON_FOUND, null, -1, -1));
                break;
        }
        return results;
    }

    private ActionResult noiseResult() {
        /*double roll = ThreadLocalRandom.current().nextDouble(0, 100);
        SearchResult searchResult =
        switch (searchResult) {
            case 1-40 :
                return new ActionResult(ActionResult.ActionResultType.NOISE_IGNORED, null, -1, -1);
            case 41-80 :
                room.addZombie(game.getCurrentRoomNumber());
                return new ActionResult(ActionResult.ActionResultType.ZOMBIE_APPEARED, null, 1, -1);
            case 80-100 :
                room.addZombie(game.getCurrentRoomNumber());
                room.addZombie(game.getCurrentRoomNumber());
                return new ActionResult(ActionResult.ActionResultType.ZOMBIE_APPEARED, null, 2, -1);
        }

         */
        return null;
    }
}