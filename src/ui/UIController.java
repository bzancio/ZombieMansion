package ui;

import results.*;

import java.util.List;

public record UIController(ConsoleUI consoleUI) {

    public void presentResult(ActionResult result) {
        switch (result.getType()) {
            case PLAYER_TURN -> consoleUI.showPlayerAttack((PlayerTurnResult) result);
            case ZOMBIE_TURN -> consoleUI.showZombieAttack((ZombieTurnResult) result);
            case PLAYER_HEALS -> consoleUI.showHealMessage();
            case ZOMBIE_DEFEAT -> consoleUI.showZombieDefeat();
            case ZOMBIE_APPEARED -> consoleUI.showZombieAppeared((ZombieAppearedResult) result);
            case KIT_FOUND -> consoleUI.showFoundKitMessage();
            case KIT_FULL -> consoleUI.showFullKitMessage();
            case WEAPON_FOUND -> consoleUI.showFoundWeaponMessage();
            case PROTECTION_FOUND -> consoleUI.showFoundProtection();
            case SEARCH_NOISE -> consoleUI.showNoiseMessage();
            case NOISE_IGNORED -> consoleUI.showNoiseIgnored();
            case ADVANCED_ROOM -> consoleUI.showNewRoomMessage((AdvancedRoomResult)result);
            case ESCAPED -> consoleUI.showEscapeMessage();
            case PLAYER_LOSE -> consoleUI.showGameOver();
            case PLAYER_SEARCHED -> consoleUI.showSearchMessage();
            case GAME_STATUS -> consoleUI.showGameStatus((GameStatusResult)result);
        }
    }

    public void presentAllResults(List<ActionResult> results) {
        for (ActionResult result : results) {
            presentResult(result);
        }
    }
}
