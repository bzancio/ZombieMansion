package state;

import actions.Action;
import events.GameNotification;
import game.Game;

import java.util.List;

public class GameStatusDTO extends GameNotification {

    private final int currentRoomNumber;
    private final int playerHp;
    private final int playerMaxHp;
    private final int playerAttackPoints;
    private final int playerNumberWeapons;
    private final int playerNumberProtections;
    private final boolean playerHasKit;
    private final int roomActiveZombies;
    private final int roomRemainingSearchs;
    private final int maxRoomNumber;
    private final List<Action> availableActions;

    public GameStatusDTO(int currentRoomNumber, int playerHp, int playerMaxHp, int playerAttackPoints, int playerNumberWeapons, int plyerNumberProtections, boolean playerHasKit, int roomActiveZombies, int roomRemainingSearchs, int maxRoomNumber, List<Action> availableActions) {
        this.currentRoomNumber = currentRoomNumber;
        this.playerHp = playerHp;
        this.playerMaxHp = playerMaxHp;
        this.playerAttackPoints = playerAttackPoints;
        this.playerNumberWeapons = playerNumberWeapons;
        this.playerNumberProtections = plyerNumberProtections;
        this.playerHasKit = playerHasKit;
        this.roomActiveZombies = roomActiveZombies;
        this.roomRemainingSearchs = roomRemainingSearchs;
        this.maxRoomNumber = maxRoomNumber;
        this.availableActions = availableActions;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.GAME_STATUS;
    }

    public int getCurrentRoomNumber() {
        return currentRoomNumber;
    }

    public int getPlayerHp() {
        return playerHp;
    }

    public int getPlayerMaxHp() {
        return playerMaxHp;
    }

    public int getPlayerAttackPoints() {
        return playerAttackPoints;
    }

    public int getPlayerNumberWeapons() {
        return playerNumberWeapons;
    }

    public int getNumberProtections() {
        return playerNumberProtections;
    }

    public boolean getHasKit() {
        return playerHasKit;
    }

    public int getRoomActiveZombies() {
        return roomActiveZombies;
    }

    public int getRoomRemainingSearchAttempts() {
        return roomRemainingSearchs;
    }

    public int getMaxRoomNumber() {
        return maxRoomNumber;
    }

    public List<Action> getAvailableActions() {
         return availableActions;
    }
}
