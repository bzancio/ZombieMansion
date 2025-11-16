package events;

public class ZombieAttackInfo extends GameNotification {
    private final int damage;

    public ZombieAttackInfo(int damage) {
        this.damage = damage;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.ZOMBIE_ATTACK;
    }

    public int getDamage() {
        return damage;
    }
}
