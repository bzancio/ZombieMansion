package actions;

import game.Game;
import game.Player;
import game.Room;
import events.*;

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
        return !game.getCurrentRoom().hasActiveZombies() && game.getCurrentRoom().getRemainingSearchAttempts() != 0;
    }

    @Override
    public List<GameNotification> execute() {
        room.setRemainingSearchAttempts(room.getRemainingSearchAttempts() - 1);
        List<GameNotification> results = new ArrayList<>();
        results.add(new DefaultEventInfo(GameNotification.NotificationType.PLAYER_SEARCHED));
        double mainRoll = ThreadLocalRandom.current().nextDouble(0, 100);

        if (mainRoll <= 75) {
            results.add(new DefaultEventInfo(GameNotification.NotificationType.SEARCH_NOISE));
            results.add(noiseResult());
        } else if (mainRoll <= 90) {
            results.add(new DefaultEventInfo(GameNotification.NotificationType.KIT_FOUND));
            if (player.getHasKit()) {
                results.add(new DefaultEventInfo(GameNotification.NotificationType.KIT_FULL));
            } else
                player.setHasKit(true);
        } else if (mainRoll <= 95) {
            player.setNumberProtections(player.getNumberProtections() + 1);
            results.add(new DefaultEventInfo(GameNotification.NotificationType.PROTECTION_FOUND));
        } else {
            player.setNumberWeapons(player.getNumberWeapons() + 1);
            results.add(new DefaultEventInfo(GameNotification.NotificationType.WEAPON_FOUND));
        }
        return results;
    }

    private GameNotification noiseResult() {
        double noiseRoll = ThreadLocalRandom.current().nextDouble(0, 100);
        if (noiseRoll <= 40)
            return new DefaultEventInfo(GameNotification.NotificationType.NOISE_IGNORED);
        room.addZombie(game.getCurrentRoomNumber());
        if (noiseRoll <= 80) {
            room.setActiveZombies(room.getActiveZombies() + 1);
            return new ZombieSpawnInfo(1);
        } else {
            room.addZombie(game.getCurrentRoomNumber());
            room.setActiveZombies(room.getActiveZombies() + 2);
            return new ZombieSpawnInfo(2);
        }
    }
}