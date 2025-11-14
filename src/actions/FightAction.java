package actions;

import game.*;
import events.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FightAction implements ActionStrategy {
    private final Room room;
    private final Player player;

    public FightAction(Game game) {
        this.room = game.getRoom();
        this.player = game.getPlayer();
    }

    @Override
    public List<GameNotification> execute() {
        List<GameNotification> results = new ArrayList<>();
        Zombie zombie = room.getZombieArray().getFirst();

        results.add(playerTurn(player, zombie));
        if (zombie.isAlive()) {
            results.add(zombieTurn(zombie, player));
            if (!player.isAlive()) {
                results.add(new DefaultEventInfo(GameNotification.NotificationType.PLAYER_LOSE));
            }
        } else {
            room.setActiveZombies(room.getActiveZombies() - 1);
            room.deleteZombie();
            results.add(new DefaultEventInfo(GameNotification.NotificationType.ZOMBIE_DEFEAT));
        }
        return results;
    }

    private GameNotification playerTurn(Player player, Zombie zombie) {
        int roll = ThreadLocalRandom.current().nextInt(1, player.getAttackPoints() + 1);
        int damage = roll + player.getNumberWeapons();
        zombie.takeDamage(damage);
        return new PlayerAttackInfo(damage, zombie.getHp());
    }

    private GameNotification zombieTurn(Zombie zombie, Player player) {
        int roll = ThreadLocalRandom.current().nextInt(1, zombie.getAttackPoints()) + 1;
        int damage = Math.max(0, (roll - player.getNumberProtections()));
        player.takeDamage(damage);
        return new ZombieAttackInfo(damage, player.getHp());
    }

    public static boolean isAvailable(boolean roomHasZombies) {
        return roomHasZombies;
    }
}