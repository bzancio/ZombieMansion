package ui;

import actions.ActionResult;

import java.util.List;

public class UIController {
    private final ConsoleUI consoleUI;

    public UIController(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
    }

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
                break;
            case SEARCH_NOISE:
                break;
            case ADVANCED_ROOM:
                break;
            case ESCAPED:
                consoleUI.showEscapeMessage();
                break;
            case PLAYER_LOSE:
                consoleUI.showGameover();
                break;
        }
    }
}
