package events;

public class PlayerAttackInfo extends GameNotification {
    private final int damage;
    private final int hp;
    public PlayerAttackInfo(int damage, int hp) {
        this.damage = damage;
        this.hp = hp;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.PLAYER_ATTACK;
    }

    public int getDamage() {
        return damage;
    }

    public int getHp() {
        return hp;
    }
}
