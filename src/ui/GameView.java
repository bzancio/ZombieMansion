package ui;

import actions.Action;
import events.RoomAdvanceInfo;
import events.ZombieSpawnInfo;
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
    private final JLabel weaponsNumberLabel;
    private final JLabel protectionsNumberLabel;
    private final JLabel hasKitLabel;
    private final JLabel attemptsLabel;

    public GameView() {
        super("La Mansión Zombie v2 - Partida");
        this.setLayout(new BorderLayout(5, 5));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.actionButtons = new HashMap<>();
        this.hpLabel = new JLabel("Vida: ---");
        this.roomLabel = new JLabel("Sala Actual: ---");
        this.protectionsNumberLabel = new JLabel("Protecciones: ---");
        this.weaponsNumberLabel = new JLabel("Armas: ---");
        this.hasKitLabel = new JLabel("Botiquin: ---");
        this.zombieLabel = new JLabel("Zombies: ---");
        this.attemptsLabel = new JLabel("Intentos: ");
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
        panel.setLayout(new GridLayout(8, 1, 10, 5));
        panel.add(this.hpLabel);
        panel.add(this.roomLabel);
        panel.add(this.attemptsLabel);
        panel.add(this.protectionsNumberLabel);
        panel.add(this.weaponsNumberLabel);
        panel.add(this.hasKitLabel);
        panel.add(this.zombieLabel);
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

    public void updateStatus(GameStatusDTO gameStatusDTO) {
        hpLabel.setText("Vida: " + gameStatusDTO.playerHp() + "/" + gameStatusDTO.playerMaxHp());
        roomLabel.setText("Habitación (max " + gameStatusDTO.maxRoomNumber() + "): " + gameStatusDTO.currentRoomNumber());
        zombieLabel.setText("Zombies: " + gameStatusDTO.roomActiveZombies());
        protectionsNumberLabel.setText("Protecciones: " + gameStatusDTO.playerNumberProtections());
        weaponsNumberLabel.setText("Armas: " + gameStatusDTO.playerNumberWeapons());
        attemptsLabel.setText("Intentos: " + gameStatusDTO.roomRemainingSearches());
        hasKitLabel.setText("Botiquin: " + (gameStatusDTO.playerHasKit() ? "Si" : "No"));

        for (Map.Entry<Action, JButton> entry : actionButtons.entrySet()) {
            Action currentAction = entry.getKey();
            JButton button = entry.getValue();

            boolean isAvailable = gameStatusDTO.availableActions().contains(currentAction);

            button.setEnabled(isAvailable);
        }
    }
    public void showZombieSpawned(ZombieSpawnInfo zombieSpawnInfo) {
        JOptionPane.showMessageDialog(null, "Aparece un zombie" , "xd", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showAdvanceRoom(RoomAdvanceInfo roomAdvanceInfo) {
        JOptionPane.showMessageDialog(null, "Avanzaste a la habitación " + (roomAdvanceInfo.getRoomNumber() + 1), "Avance", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showDefaultEventInfo(String title, String eventInfo) {
        JOptionPane.showMessageDialog(
                null,
                eventInfo,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
