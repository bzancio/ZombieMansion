package actions;

import game.Game;
import events.GameNotification;
import events.RoomAdvanceInfo;

import java.util.ArrayList;
import java.util.List;

public record AdvanceAction(Game game) implements ActionStrategy {

    public static boolean isAvailable(Game game) {
        return !game.getCurrentRoom().hasActiveZombies() && (game.getCurrentRoomNumber() != game.getDifficulty().getRoomNumber());
    }

    @Override
    public List<GameNotification> execute() {
        List<GameNotification> results = new ArrayList<>();
        game.advanceRoom();
        results.add(new RoomAdvanceInfo(game.getCurrentRoomNumber()));
        return results;
    }
}
