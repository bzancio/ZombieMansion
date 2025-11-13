package ui;

import actions.Action;
import game.Game;
import state.GameStatusDTO;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameView extends JFrame {
    private Game game;
    private final Map<actions.Action, JButton> actionButtons;
    private final JLabel hpLabel;
    private final JLabel roomLabel;
    private final JLabel zombieLabel;

    public GameView() {
        super("La Mansión Zombie v2 - Partida");
        this.setLayout(new BorderLayout(5, 5));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.actionButtons = new HashMap<>();
        this.hpLabel = new JLabel("Vida: ---");
        this.roomLabel = new JLabel("Sala Actual: ---");
        this.zombieLabel = new JLabel("Zombies: ---");
    }

    public void setupGameView(Game game) {
        this.game = game;
        this.setPreferredSize(new Dimension(800, 600));
        this.add(createStatusPanel(), BorderLayout.NORTH);
        this.add(createActionPanel(), BorderLayout.EAST);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JPanel createStatusPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 5));
        panel.add(this.hpLabel);
        panel.add(this.roomLabel);
        panel.add(new JLabel("Armas: [X]"));
        panel.add(new JLabel("Protecciones: [X]"));
        panel.add(this.zombieLabel);
        panel.add(new JLabel("Búsquedas: [X]"));
        panel.add(new JLabel("Kit: [Sí/No]"));
        return panel;
    }

    private JPanel createActionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        for (Action action : Action.values()) {
            JButton actionButton = new JButton(action.getLabel());
            actionButton.addActionListener(e -> {
                if (game != null) {
                    game.performAction(action);
                }
            });
            this.actionButtons.put (action, actionButton);
            panel.add(actionButton);
        }
        return panel;
    }

    public void updateStatus(GameStatusDTO result) {
        hpLabel.setText("Vida: " + result.getPlayerMaxHp());
        roomLabel.setText("Sala Actual: " + result.getCurrentRoomNumber());
        zombieLabel.setText("Zombies: " + result.getRoomActiveZombies());

        for (Map.Entry<Action, JButton> entry : actionButtons.entrySet()) {
            Action currentAction = entry.getKey();
            JButton button = entry.getValue();

            boolean isAvailable = result.getAvailableActions().contains(currentAction);

            button.setEnabled(isAvailable);
        }
    }
}
