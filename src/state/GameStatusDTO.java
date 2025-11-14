package state;

import actions.Action;
import actions.AdvanceAction;
import actions.EscapeAction;
import actions.FightAction;
import actions.HealAction;
import actions.SearchAction;
import game.Game;
import game.Player;
import game.Room;

import java.util.ArrayList;
import java.util.List;

public record GameStatusDTO(int currentRoomNumber, int playerHp, int playerMaxHp, int playerAttackPoints,
                            int playerNumberWeapons, int playerNumberProtections, boolean playerHasKit,
                            int roomActiveZombies, int roomRemainingSearches, int maxRoomNumber,
                            List<Action> availableActions) {

    public static GameStatusDTO buildFrom(Game game) {

        List<Action> availableActions = calculateAvailableActions(game);

        return new GameStatusDTO(
                game.getRoom().getRoomNumber(),
                game.getPlayer().getHp(),
                game.getPlayer().getMaxHp(),
                game.getPlayer().getAttackPoints(),
                game.getPlayer().getNumberWeapons(),
                game.getPlayer().getNumberProtections(),
                game.getPlayer().getHasKit(),
                game.getRoom().getActiveZombies(),
                game.getRoom().getRemainingSearchAttempts(),
                game.getDifficulty().getRoomNumber(),
                availableActions
        );
    }

    private static List<Action> calculateAvailableActions(Game game) {
        List<Action> actions = new ArrayList<>();

        Room room = game.getRoom();
        Player player = game.getPlayer();
        boolean roomHasZombies = room.hasActiveZombies();
        int maxRooms = game.getDifficulty().getRoomNumber();
        int currentRoom = room.getRoomNumber();

        if (FightAction.isAvailable(roomHasZombies)) {
            actions.add(Action.FIGHT);
        } else {
            if (SearchAction.isAvailable(player.getHasKit(), room.getRemainingSearchAttempts())) {
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