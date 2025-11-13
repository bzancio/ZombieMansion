package events;

public abstract class GameNotification {

    public abstract NotificationType getType();

    public enum NotificationType {
        PLAYER_ATTACK,
        ZOMBIE_ATTACK,
        PLAYER_HEALS,
        ZOMBIE_DEFEAT,
        ZOMBIE_SPAWN,
        KIT_FOUND,
        KIT_FULL,
        WEAPON_FOUND,
        PROTECTION_FOUND,
        SEARCH_NOISE,
        NOISE_IGNORED,
        ADVANCED_ROOM,
        ESCAPED,
        PLAYER_LOSE,
        PLAYER_SEARCHED,
        GAME_STATUS
    }
}

