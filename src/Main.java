import ui.*;

import javax.swing.*;

public class Main {
    private MenuView menuView;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::startGame);
    }

    public static void startGame() {
        MenuView menuView = new MenuView(null);
        GameView gameView = new GameView();
        ViewController viewController = new ViewController(menuView);
        menuView.setMenuDelegate(viewController);
        menuView.setVisible(true);
    }
}