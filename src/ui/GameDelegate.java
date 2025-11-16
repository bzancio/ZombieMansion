package ui;

import actions.Action;

public interface GameDelegate {
    void handleGameAction(Action action);
    void showCombatView();
    void exitGameView();
}
