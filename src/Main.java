import game.Difficulty;
import game.Game;
import ui.*;

import javax.swing.*;

public class Main implements MenuDelegate {
    public static void main(String[] args) {
        Main app = new Main();
        SwingUtilities.invokeLater(() -> new MenuView(app));
    }

    @Override
    public void startGame(Difficulty difficulty) {
        GameView gameView = new GameView();
        UIController uiController = new UIController(gameView);
        Game game = new Game(uiController, difficulty);
        gameView.setupGameView(game);
        game.start();
    }

    @Override
    public void loadGame() {

    }

    @Override
    public void viewHistory() {

    }
}