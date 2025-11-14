package actions;

import events.DefaultEventInfo;
import events.GameNotification;

import java.util.ArrayList;
import java.util.List;

public class EscapeAction implements ActionStrategy {

    public static boolean isAvailable(boolean roomHasActiveZombies, int roomNumber, int maxRoomNumber) {
        return !roomHasActiveZombies && (roomNumber == maxRoomNumber);
    }

    @Override
    public List<GameNotification> execute() {
        List<GameNotification> results = new ArrayList<>();
        results.add(new DefaultEventInfo(GameNotification.NotificationType.ESCAPED));
        return results;
    }
}
