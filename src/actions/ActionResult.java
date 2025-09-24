package actions;

public record ActionResult(ActionResultType type, String message, int data1, int data2) {
    public enum ActionResultType {
        PLAYER_HEALS,
        PLAYER_TURN,
        PLAYER_SEARCHED,
        ZOMBIE_TURN,
        ZOMBIE_DEFEAT,
        SEARCH_NOISE,
        NOISE_IGNORED,
        ZOMBIE_APPEARED,
        WEAPON_FOUND,
        PROTECTION_FOUND,
        KIT_FOUND,
        KIT_FULL,
        ADVANCED_ROOM,
        ESCAPED,
        PLAYER_LOSE
    }
}
