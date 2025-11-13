package actions;

import events.DefaultEventInfo;
import game.Game;
import events.GameNotification;

import java.util.ArrayList;
import java.util.List;

public class EscapeAction implements ActionStrategy {

    public static boolean isAvailable(Game game) {
        return !game.getCurrentRoom().hasActiveZombies() && (game.getCurrentRoomNumber() == game.getDifficulty().getRoomNumber());
    }

    @Override
    public List<GameNotification> execute() {
        List<GameNotification> results = new ArrayList<>();
        results.add(new DefaultEventInfo(GameNotification.NotificationType.ESCAPED));
        return results;
    }
}
