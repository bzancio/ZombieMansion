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
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CombatView extends JDialog {
    private final CombatDelegate combatDelegate;
    private JButton fightButton;
    private JButton exitButton;
    private JTextPane playerHpField;
    private JTextPane zombieHpField;
    private JTextPane playerAttackField;
    private JTextPane zombieAttackField;
    private JTextArea combatLog;
    private Image backgroundImage;

    public CombatView(JFrame owner, CombatDelegate combatDelegate) {
        super(owner, true);
        this.setTitle("La Mansión Zombie v2 - Combate");
        this.combatDelegate = combatDelegate;
        loadBackground();
        setupWindow();
        setupListeners();
    }

    private void loadBackground() {
        try {
            backgroundImage = ImageIO.read(new File("src/resources/images/combatmenu.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setupWindow() {
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);

        JPanel backgroundPanel = new JPanel(new BorderLayout(10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        this.setContentPane(backgroundPanel);

        JPanel statusPanel = new JPanel(new GridLayout(5, 2, 10, 5));
        statusPanel.setOpaque(false);

        JLabel playerHpLabel = new JLabel("Vida Jugador:");
        playerHpLabel.setForeground(Color.WHITE);
        statusPanel.add(playerHpLabel);
        playerHpField = new JTextPane();
        playerHpField.setEditable(false);
        playerHpField.setFocusable(false);
        statusPanel.add(playerHpField);

        JLabel playerAttackLabel = new JLabel("Jugador ATK:");
        playerAttackLabel.setForeground(Color.WHITE);
        statusPanel.add(playerAttackLabel);
        playerAttackField = new JTextPane();
        playerAttackField.setEditable(false);
        playerAttackField.setFocusable(false);
        statusPanel.add(playerAttackField);

        JLabel zombieHpLabel = new JLabel("Vida Zombie");
        zombieHpLabel.setForeground(Color.WHITE);
        statusPanel.add(zombieHpLabel);
        zombieHpField = new JTextPane();
        zombieHpField.setEditable(false);
        zombieHpField.setFocusable(false);
        statusPanel.add(zombieHpField);

        JLabel zombieAttackLabel = new JLabel("Zombie ATK:");
        zombieAttackLabel.setForeground(Color.WHITE);
        statusPanel.add(zombieAttackLabel);
        zombieAttackField = new JTextPane();
        zombieAttackField.setEditable(false);
        zombieAttackField.setFocusable(false);
        statusPanel.add(zombieAttackField);

        combatLog = new JTextArea();
        combatLog.setEditable(false);
        combatLog.setFocusable(false);

        backgroundPanel.add(statusPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(combatLog);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        actionPanel.setOpaque(false);
        fightButton = new JButton("Luchar");
        exitButton = new JButton("Terminar");

        actionPanel.add(fightButton);
        actionPanel.add(exitButton);

        backgroundPanel.add(actionPanel, BorderLayout.SOUTH);
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