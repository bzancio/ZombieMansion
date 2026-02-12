package actions;

import game.Game;
import game.Player;
import game.Room;

import java.util.ArrayList;
import java.util.List;

public class ActionAvailabilityPolicy {
    public static List<Action> calculate(Game game) {
        List<Action> actions = new ArrayList<>();

        Room room = game.getRoom();
        Player player = game.getPlayer();
        boolean roomHasZombies = room.hasActiveZombies();
        int maxRooms = game.getDifficulty().getRoomNumber();
        int currentRoom = room.getRoomNumber();

        if (FightAction.isAvailable(roomHasZombies)) {
            actions.add(Action.FIGHT);
        } else {
            if (SearchAction.isAvailable(false, room.getRemainingSearchAttempts())) {
                actions.add(Action.SEARCH);
            }
            if (HealAction.isAvailable(false, player.getHasKit())) {
                actions.add(Action.HEAL);
            }
            if (AdvanceAction.isAvailable(false, currentRoom, maxRooms)) {
                actions.add(Action.ADVANCE);
            }
            if (EscapeAction.isAvailable(false, currentRoom, maxRooms)) {
                actions.add(Action.ESCAPE);
            }
        }

        return actions;
    }
}
