package state;

import actions.Action;
import actions.ActionAvailabilityPolicy;
import game.Game;

import java.io.Serializable;
import java.util.List;

public record GameStatusDTO(int currentRoomNumber, int playerHp, int playerMaxHp, int playerAttackPoints,
                            int playerNumberWeapons, int playerNumberProtections, boolean playerHasKit,
                            int roomActiveZombies, int roomRemainingSearches, int maxRoomNumber, int zombieHp, int zombieAttackPoints,
                            List<Action> availableActions) implements Serializable {

    public static GameStatusDTO buildFrom(Game game) {

        int zombieHp = 0;
        int zombieAttackPoints = 0;
        if (game.getRoom().hasActiveZombies()) {
            zombieHp = game.getRoom().getZombieArray().getFirst().getHp();
            zombieAttackPoints = game.getRoom().getZombieArray().getFirst().getAttackPoints();
        }

        List<Action> availableActions = ActionAvailabilityPolicy.calculate(game);

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
                zombieHp,
                zombieAttackPoints,
                availableActions
        );
    }
}
