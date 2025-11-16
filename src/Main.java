import ui.*;

import javax.swing.*;

public class Main {
    private MenuView menuView;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::startGame);
    }

    public static void startGame() {
        MenuView menuView = new MenuView(null);
        ViewController viewController = new ViewController(menuView);
    }
}