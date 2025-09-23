package ui;

import actions.ActionResult;

import java.util.List;

public record UIController(ConsoleUI consoleUI) {

    public void presentAllResults(List<ActionResult> results) {
        for (ActionResult result : results) {
            presentResult(result);
        }
    }

    public void presentResult(ActionResult result) {
        switch (result.type()) {
            case PLAYER_HEALS:
                consoleUI.showHealMessage();
                break;
            case PLAYER_TURN:
                consoleUI.showPlayerAttack(result.data1(), result.data2());
                break;
            case ZOMBIE_TURN:
                consoleUI.showZombieAttack(result.data1(), result.data2());
                break;
            case ZOMBIE_DEFEAT:
                consoleUI.showZombieDefeat();
                break;
            case ZOMBIE_APPEARED:
                consoleUI.showZombieAppeared(result.data1());
                break;
            case KIT_FOUND:
                consoleUI.showFoundKitMessage();
                break;
            case KIT_FULL:
                consoleUI.showFullKitMessage();
                break;
            case WEAPON_FOUND:
                consoleUI.showFoundWeaponMessage();
                break;
            case PROTECTION_FOUND:
                consoleUI.showFoundProtection();
                break;
            case SEARCH_NOISE:
                consoleUI.showNoiseMessage();
                break;
            case NOISE_IGNORED:
                consoleUI.showNoiseIgnored();
                break;
            case ADVANCED_ROOM:
                consoleUI.showNewRoomMessage(result.data1());
                break;
            case ESCAPED:
                consoleUI.showEscapeMessage();
                break;
            case PLAYER_LOSE:
                consoleUI.showGameOver();
                break;
        }
    }
}
