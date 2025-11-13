package actions;

import events.DefaultEventInfo;
import game.Game;
import game.Player;
import events.GameNotification;

import java.util.ArrayList;
import java.util.List;

public record HealAction(Player player) implements ActionStrategy {

    public static boolean isAvailable(Game game) {
        return !game.getCurrentRoom().hasActiveZombies() && game.getPlayer().getHasKit();
    }

    @Override
    public List<GameNotification> execute() {
        List<GameNotification> results = new ArrayList<>();
        player.useKit();
        results.add(new DefaultEventInfo(GameNotification.NotificationType.PLAYER_HEALS));
        return results;
    }
}