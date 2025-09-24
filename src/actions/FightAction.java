package actions;

import game.*;
import results.*;

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
                results.add(new PlayerLoseResult());
            }
        } else {
            room.setActiveZombies(room.getActiveZombies() - 1);
            room.deleteZombie();
            results.add(new ZombieDefeatResult());
        }
        return results;
    }

    private ActionResult playerTurn(Player player, Zombie zombie) {
        int roll = ThreadLocalRandom.current().nextInt(1, player.getAttackPoints() + 1);
        int damage = roll + player.getNumberWeapons();
        zombie.takeDamage(damage);
        return new PlayerTurnResult(damage, zombie.getHp());
    }

    private ActionResult zombieTurn(Zombie zombie, Player player) {
        int roll = ThreadLocalRandom.current().nextInt(1, zombie.getAttackPoints()) + 1;
        int damage = Math.max(0, (roll - player.getNumberProtections()));
        player.takeDamage(damage);
        return new ZombieTurnResult(damage, player.getHp());
    }

    public static boolean isAvailable(Game game) {
        return game.getCurrentRoom().hasActiveZombies();
    }
}