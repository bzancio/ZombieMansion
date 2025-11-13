package events;

public class ZombieSpawnInfo extends GameNotification {
    private final int zombieNumber;

    public ZombieSpawnInfo(int zombieNumber) {
        this.zombieNumber = zombieNumber;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.ZOMBIE_SPAWN;
    }
    public int getZombieNumber() {
        return zombieNumber;
    }
}
