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
    private final JTextPane hpField;
    private final JLabel roomLabel;
    private final JTextPane roomField;
    private final JLabel zombieLabel;
    private final JTextPane zombieField;
    private final JLabel weaponsNumberLabel;
    private final JTextPane weaponsNumberField;
    private final JLabel protectionsNumberLabel;
    private final JTextPane protectionsNumberField;
    private final JLabel hasKitLabel;
    private final JTextPane hasKitField;
    private final JLabel attemptsLabel;
    private final JTextPane attemptsField;
    private final JButton saveButton;

    public GameView() {
        super("La Mansión Zombie v2 - Partida");
        this.setLayout(new BorderLayout(5, 5));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.actionButtons = new HashMap<>();

        this.hpLabel = new JLabel("Vida:");
        this.hpField = new JTextPane();

        this.roomLabel = new JLabel("Sala Actual:");
        this.roomField = new JTextPane();

        this.protectionsNumberLabel = new JLabel("Protecciones:");
        this.protectionsNumberField = new JTextPane();

        this.weaponsNumberLabel = new JLabel("Armas:");
        this.weaponsNumberField = new JTextPane();

        this.hasKitLabel = new JLabel("Botiquin:");
        this.hasKitField = new JTextPane();

        this.zombieLabel = new JLabel("Zombies:");
        this.zombieField = new JTextPane();

        this.attemptsLabel = new JLabel("Intentos:");
        this.attemptsField = new JTextPane();

        this.saveButton = new JButton("Guardar");
    }

    public void setupGameView(Game game) {
        this.game = game;
        this.setPreferredSize(new Dimension(600, 400));
        this.add(createSavePanel(), BorderLayout.NORTH);
        this.add(createStatusPanel(), BorderLayout.CENTER);
        this.add(createActionPanel(), BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private JPanel createStatusPanel() {
        JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(createStatusRow(this.hpLabel, this.hpField));
        mainPanel.add(createStatusRow(this.roomLabel, this.roomField));
        mainPanel.add(createStatusRow(this.attemptsLabel, this.attemptsField));
        mainPanel.add(createStatusRow(this.protectionsNumberLabel, this.protectionsNumberField));
        mainPanel.add(createStatusRow(this.weaponsNumberLabel, this.weaponsNumberField));
        mainPanel.add(createStatusRow(this.hasKitLabel, this.hasKitField));
        mainPanel.add(createStatusRow(this.zombieLabel, this.zombieField));

        outerPanel.add(mainPanel);
        return outerPanel;
    }

    private JPanel createStatusRow(JLabel label, JTextPane field) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        field.setPreferredSize(new Dimension(40, 20));
        field.setEditable(false);
        field.setFocusable(false);
        label.setPreferredSize(new Dimension(80, 20));
        rowPanel.add(label);
        rowPanel.add(field);
        return rowPanel;
    }

    private JPanel createActionPanel() {
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));

        for (Action action : Action.values()) {
            JButton actionButton = new JButton(action.getLabel());
            actionButton.setPreferredSize(new Dimension(100, 30));

            if (action == Action.FIGHT) {
                actionButton.addActionListener(e -> {
                });
            }
            else {
                actionButton.addActionListener(e -> {
                    if (game != null) {
                        game.performAction(action);
                    }
                });
            }
            this.actionButtons.put(action, actionButton);
            actionPanel.add(actionButton);
        }

        actionPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        return actionPanel;
    }

    private JPanel createSavePanel() {
        JPanel savePanel = new JPanel();
        savePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        savePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        saveButton.setPreferredSize(new Dimension(100, 30));
        saveButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Not implemented yet"));
        savePanel.add(saveButton);
        return savePanel;
    }

    public void updateStatus(GameStatusDTO gameStatusDTO) {
        hpField.setText(gameStatusDTO.playerHp() + "/" + gameStatusDTO.playerMaxHp());
        roomField.setText(gameStatusDTO.currentRoomNumber() + "/" + gameStatusDTO.maxRoomNumber());
        zombieField.setText("" + gameStatusDTO.roomActiveZombies());
        protectionsNumberField.setText("" + gameStatusDTO.playerNumberProtections());
        weaponsNumberField.setText("" + gameStatusDTO.playerNumberWeapons());
        attemptsField.setText("" + gameStatusDTO.roomRemainingSearches());
        hasKitField.setText((gameStatusDTO.playerHasKit() ? "Si" : "No"));

        for (Map.Entry<Action, JButton> entry : actionButtons.entrySet()) {
            Action currentAction = entry.getKey();
            JButton button = entry.getValue();

            boolean isAvailable = gameStatusDTO.availableActions().contains(currentAction);
            button.setEnabled(isAvailable);
        }
    }

    public void showZombieSpawned(ZombieSpawnInfo zombieSpawnInfo) {
        JOptionPane.showMessageDialog(null, zombieSpawnInfo.getZombieNumber() == 1 ? "Aparece un zombie" : "Aparecen dos zombies", "Zombie", JOptionPane.INFORMATION_MESSAGE);
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