package results;

import game.Game;

public class GameStatusResult extends ActionResult {
    private final Game game;

    public GameStatusResult(Game game) {
        this.game = game;
    }

    @Override
    public ActionResultType getType() {
        return ActionResultType.GAME_STATUS;
    }

    public int getCurrentRoomNumber() {
        return game.getCurrentRoomNumber();
    }

    public int getPlayerHp() {
        return game.getPlayer().getHp();
    }

    public int getPlayerMaxHp() {
        return game.getPlayer().getMaxHp();
    }

    public int getPlayerAttackPoints() {
        return game.getPlayer().getAttackPoints();
    }

    public int getPlayerNumberWeapons() {
        return game.getPlayer().getNumberWeapons();
    }

    public int getNumberProtections() {
        return game.getPlayer().getNumberProtections();
    }

    public boolean getHasKit() {
        return game.getPlayer().getHasKit();
    }

    public int getRoomActiveZombies() {
        return game.getCurrentRoom().getActiveZombies();
    }

    public int getRoomRemainingSearchAttempts() {
        return game.getCurrentRoom().getRemainingSearchAttempts();
    }

    public int getMaxRoomNumber() {
        return game.getDifficulty().getRoomNumber();
    }
}
