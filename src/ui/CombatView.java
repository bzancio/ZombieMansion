package ui;

import actions.Action;
import events.PlayerAttackInfo;
import events.ZombieAttackInfo;
import state.GameStatusDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CombatView extends JDialog {
    private final CombatDelegate combatDelegate;
    private JButton fightButton;
    private JButton exitButton;
    private JTextPane playerHpField;
    private JTextPane zombieHpField;
    private JTextPane playerAttackField;
    private JTextPane zombieAttackField;
    private JTextArea combatLog;

    public CombatView(JFrame owner, CombatDelegate combatDelegate) {
        super(owner, true);
        this.combatDelegate = combatDelegate;
        setupWindow();
        setupListeners();
    }

    private void setupWindow() {
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10));
        JPanel statusPanel = new JPanel(new GridLayout(5, 2, 10, 5));

        JLabel playerHpLabel = new JLabel("Vida Jugador:");
        statusPanel.add(playerHpLabel);
        playerHpField = new JTextPane();
        playerHpField.setEditable(false);
        playerHpField.setFocusable(false);
        statusPanel.add(playerHpField);

        JLabel playerAttackLabel = new JLabel("Jugador ATK:");
        statusPanel.add(playerAttackLabel);
        playerAttackField = new JTextPane();
        playerAttackField.setEditable(false);
        playerAttackField.setFocusable(false);
        statusPanel.add(playerAttackField);

        JLabel zombieHpLabel = new JLabel("Vida Zombie");
        statusPanel.add(zombieHpLabel);
        zombieHpField = new JTextPane();
        zombieHpField.setEditable(false);
        zombieHpField.setFocusable(false);
        statusPanel.add(zombieHpField);

        JLabel zombieAttackLabel = new JLabel("Zombie ATK:");
        statusPanel.add(zombieAttackLabel);
        zombieAttackField = new JTextPane();
        zombieAttackField.setEditable(false);
        zombieAttackField.setFocusable(false);
        statusPanel.add(zombieAttackField);

        combatLog = new JTextArea();
        combatLog.setEditable(false);
        combatLog.setFocusable(false);

        this.add(statusPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(combatLog);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        fightButton = new JButton("Luchar");
        exitButton = new JButton("Terminar");

        actionPanel.add(fightButton);
        actionPanel.add(exitButton);

        this.add(actionPanel, BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(500, 300));
        this.pack();
        this.setLocationRelativeTo(getOwner());
        this.setResizable(false);
    }

    private void setupListeners() {
        fightButton.addActionListener(e -> combatDelegate.performCombatTurn());
        exitButton.addActionListener(e -> combatDelegate.exitCombatView());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                combatDelegate.exitCombatView();
            }
        });
    }

    public void setupLossListeners() {
        for (ActionListener listener : exitButton.getActionListeners()) {
            exitButton.removeActionListener(listener);
        }

        exitButton.addActionListener(e -> combatDelegate.handlePlayerLose());
        exitButton.setEnabled(true);
        fightButton.setEnabled(false);

        for (java.awt.event.WindowListener listener : this.getWindowListeners()) {
            this.removeWindowListener(listener);
        }
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                combatDelegate.handlePlayerLose();
            }
        });
    }

    public void updateStatus(GameStatusDTO gameStatusDTO) {
        Action action = Action.FIGHT;
        boolean isAvailable = gameStatusDTO.availableActions().contains(action);
        this.fightButton.setEnabled(isAvailable);
        this.exitButton.setEnabled(!isAvailable);

        this.playerHpField.setText("" + gameStatusDTO.playerHp());
        this.playerAttackField.setText("" + gameStatusDTO.playerAttackPoints());
        this.zombieHpField.setText("" + gameStatusDTO.zombieHp());
        this.zombieAttackField.setText("" + gameStatusDTO.zombieAttackPoints());
    }

    public void updateCombatLog(ZombieAttackInfo zombieAttackInfo) {
        this.combatLog.append("El zombie ataca con valor " + zombieAttackInfo.getDamage());
        this.combatLog.append("\n");
        this.combatLog.setCaretPosition(this.combatLog.getDocument().getLength());
    }

    public void updateCombatLog(PlayerAttackInfo playerAttackInfo) {
        this.combatLog.append("El superviviente ataca con valor " + playerAttackInfo.getDamage());
        this.combatLog.append("\n");
        this.combatLog.setCaretPosition(this.combatLog.getDocument().getLength());
    }

    public void clearCombatLog() {
        this.combatLog.setText("");
    }
}
