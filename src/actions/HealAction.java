package actions;

import events.DefaultEventInfo;
import events.GameNotification;

import java.util.ArrayList;
import java.util.List;

public record HealAction(Runnable useKitFunction) implements ActionStrategy {

    public static boolean isAvailable(boolean roomHasActiveZombies, boolean playerHasKit) {
        return !roomHasActiveZombies && playerHasKit;
    }

    @Override
    public List<GameNotification> execute() {
        List<GameNotification> results = new ArrayList<>();
        useKitFunction.run();
        results.add(new DefaultEventInfo(GameNotification.NotificationType.PLAYER_HEALS));
        return results;
    }
}