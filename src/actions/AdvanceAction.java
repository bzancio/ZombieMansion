package actions;

import game.Game;
import events.GameNotification;
import events.RoomAdvanceInfo;

import java.util.ArrayList;
import java.util.List;

public record AdvanceAction(Runnable roomAdvancer, int roomNumber) implements ActionStrategy {

    public static boolean isAvailable(boolean roomHasActiveZombies, int roomNumber, int maxRoomNumber) {
        return !roomHasActiveZombies && roomNumber != maxRoomNumber;
    }

    @Override
    public List<GameNotification> execute() {
        List<GameNotification> results = new ArrayList<>();
        roomAdvancer.run();
        results.add(new RoomAdvanceInfo(roomNumber));
        return results;
    }
}
