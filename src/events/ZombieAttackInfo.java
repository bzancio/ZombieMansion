package events;

public class ZombieAttackInfo extends GameNotification {
    private final int damage;
    private final int hp;

    public ZombieAttackInfo(int damage, int hp) {
        this.damage = damage;
        this.hp = hp;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.ZOMBIE_ATTACK;
    }

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }
}
