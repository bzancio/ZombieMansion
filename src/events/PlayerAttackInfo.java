package events;

public class PlayerAttackInfo extends GameNotification {
    private final int damage;

    public PlayerAttackInfo(int damage) {
        this.damage = damage;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.PLAYER_ATTACK;
    }

    public int getDamage() {
        return damage;
    }
}