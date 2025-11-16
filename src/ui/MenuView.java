package ui;

import game.Difficulty;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MenuView extends JFrame {
    private MenuDelegate menuDelegate;
    JPanel panel;
    JPanel playRowPanel;
    JButton playButton;
    JButton loadButton;
    JButton historicButton;
    JComboBox<Difficulty> difficultySelector;
    Dimension buttonSize = new Dimension(150,30);
    private Image backgroundImage;

    public MenuView(MenuDelegate menuDelegate) {
        super("La Mansión Zombie v2 - Menu");
        this.menuDelegate = menuDelegate;
        loadBackground();
        setupWindow();
        setupListeners();
    }

    private void loadBackground() {
        try {
            backgroundImage = ImageIO.read(new File("src/resources/images/mainmenu.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setMenuDelegate(MenuDelegate menuDelegate) {
        this.menuDelegate = menuDelegate;
    }

    public void setupWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600,350));
        this.setResizable(false);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        this.add(panel);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        playButton = new JButton("Jugar");
        playButton.setPreferredSize(buttonSize);

        difficultySelector = new JComboBox<>(Difficulty.values());
        difficultySelector.setPreferredSize(buttonSize);

        playRowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        playRowPanel.setOpaque(false);
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

        loadButton.addActionListener(e -> menuDelegate.loadGame());

        historicButton.addActionListener(e -> menuDelegate.viewHistory());
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