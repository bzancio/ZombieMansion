package ui;

import game.Difficulty;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {
    private MenuDelegate menuDelegate;
    JPanel panel;
    JPanel playRowPanel;
    JButton playButton;
    JButton loadButton;
    JButton historicButton;
    JComboBox<Difficulty> difficultySelector;
    Dimension buttonSize = new Dimension(150,30);

    public MenuView(MenuDelegate menuDelegate) {
        super("La Mansión Zombie v2 - Menu");
        this.menuDelegate = menuDelegate;
        setupWindow();
        setupListeners();
    }

    public void setMenuDelegate(MenuDelegate menuDelegate) {
        this.menuDelegate = menuDelegate;
    }

    public void setupWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel = new JPanel());
        this.setPreferredSize(new Dimension(600,350));
        this.setResizable(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        playButton = new JButton("Jugar");
        playButton.setPreferredSize(buttonSize);

        difficultySelector = new JComboBox<>(Difficulty.values());
        difficultySelector.setPreferredSize(buttonSize);

        playRowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        playRowPanel.add(playButton);
        playRowPanel.add(difficultySelector);

        playRowPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        playRowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, playRowPanel.getPreferredSize().height));

        loadButton = new JButton("Cargar");
        loadButton.setPreferredSize(buttonSize);
        loadButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        historicButton = new JButton("Ver historico");
        historicButton.setPreferredSize(buttonSize);
        historicButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(playRowPanel);
        panel.add(Box.createVerticalStrut(30));
        panel.add(loadButton);
        panel.add(Box.createVerticalStrut(30));
        panel.add(historicButton);
        panel.add(Box.createVerticalGlue());

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setupListeners() {
        playButton.addActionListener(e -> {
            Difficulty selectedDifficulty = (Difficulty)difficultySelector.getSelectedItem();
            this.setVisible(false);
            menuDelegate.showGameView(selectedDifficulty);
        });

        loadButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Not implemented yet"));

        historicButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Not implemented yet"));
    }
}
