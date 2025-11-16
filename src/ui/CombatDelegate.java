package ui;

public interface CombatDelegate {
    void showCombatView();
    void performCombatTurn();
    void exitCombatView();
    void handlePlayerLose();
}
