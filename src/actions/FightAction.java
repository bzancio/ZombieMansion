package actions;

import game.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FightAction implements ActionStrategy {
    private final Room room;
    private final Player player;

    public FightAction(Game game) {
        this.room = game.getCurrentRoom();
        this.player = game.getPlayer();
    }

    @Override
    public List<ActionResult> execute() {
        List<ActionResult> results = new ArrayList<>();
        Zombie zombie = room.getZombieArray().getFirst();

        results.add(playerTurn(player, zombie));
        if (zombie.isAlive()) {
            results.add(zombieTurn(zombie, player));
            if (!player.isAlive()) {
                results.add(new ActionResult(ActionResult.ActionResultType.PLAYER_LOSE, null, -1, -1));
            }
        } else {
            room.setActiveZombies(room.getActiveZombies() - 1);
            room.deleteZombie();
            results.add(new ActionResult(ActionResult.ActionResultType.ZOMBIE_DEFEAT, null, -1, -1));
        }
        return results;
    }

    private ActionResult playerTurn(Player player, Zombie zombie) {
        double roll = ThreadLocalRandom.current().nextDouble(0, player.getAttackPoints());
        int damage = (int)roll + player.getNumberWeapons();
        zombie.takeDamage(damage);
        return new ActionResult(ActionResult.ActionResultType.PLAYER_TURN, null, damage, zombie.getHp());
    }

    private ActionResult zombieTurn(Zombie zombie, Player player) {
        double roll = ThreadLocalRandom.current().nextDouble(0, zombie.getAttackPoints());
        int damage = Math.max(0, (int)roll - player.getNumberProtections());
        player.takeDamage(damage);
        return new ActionResult(ActionResult.ActionResultType.ZOMBIE_TURN, null, damage, player.getHp());
    }

    public static boolean isAvailable(Game game) {
        return game.getCurrentRoom().hasActiveZombies();
    }
}