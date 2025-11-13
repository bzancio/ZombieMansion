package ui;

import game.Difficulty;

public interface MenuDelegate {
    void startGame(Difficulty difficulty);
    void loadGame();
    void viewHistory();
}
