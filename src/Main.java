import ui.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::startGame);
    }

    public static void startGame() {
        MenuView menuView = new MenuView(null);
        new ViewController(menuView);
    }
}