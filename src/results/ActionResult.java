package results;

public abstract class ActionResult {

    public abstract ActionResultType getType();

    public enum ActionResultType {
        PLAYER_TURN,
        ZOMBIE_TURN,
        PLAYER_HEALS,
        ZOMBIE_DEFEAT,
        ZOMBIE_APPEARED,
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

