package ui;

import actions.Action;
import state.GameStatusDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CombatView extends JDialog {
    private final CombatDelegate combatDelegate;
    private JButton fightButton;
    private JButton exitButton;
    private JPanel panel;

    public CombatView(JFrame owner, CombatDelegate combatDelegate) {
        super(owner, true);
        this.combatDelegate = combatDelegate;
        setupWindow();
        setupListeners();
    }

    private void setupWindow() {
        this.panel = new JPanel();
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        panel.add(fightButton = new JButton("Luchar"));
        panel.add(exitButton = new JButton("Terminar"));
        exitButton.setEnabled(false);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        this.add(panel);
        this.setPreferredSize(new Dimension(400, 200));
        this.pack();
        this.setLocationRelativeTo(getOwner());
        this.setResizable(false);
    }

    private void setupListeners() {
        fightButton.addActionListener(e ->{
            combatDelegate.performCombatTurn();
        });
        exitButton.addActionListener(e ->{
            combatDelegate.exitCombat();
        });
    }

    public void updateStatus(GameStatusDTO gameStatusDTO) {
        Action action = Action.FIGHT;
        boolean isAvailable = gameStatusDTO.availableActions().contains(action);
        this.fightButton.setEnabled(isAvailable);
        this.exitButton.setEnabled(!isAvailable);
    }
}
