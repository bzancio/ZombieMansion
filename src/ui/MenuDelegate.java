package ui;

import game.Difficulty;

public interface MenuDelegate {
    void showGameView(Difficulty difficulty);
    void loadGame();
    void viewHistory();
}
