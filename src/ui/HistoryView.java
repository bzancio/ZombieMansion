package ui;

import state.GameStatusDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryView extends JDialog {

    private final List<GameStatusDTO> allHistory;
    private JComboBox<String> filterSelector;
    private JPanel tableContainer;

    private static final String ALL = "TODOS";
    private static final String WINS = "VICTORIA";
    private static final String LOSSES = "DERROTA";

    private final String[] columnNames = {
            "Resultado", "Dificultad", "Habitación", "PV Restantes",
            "¿Botiquín?", "Armas", "Protecciones"
    };

    public HistoryView(JFrame owner, List<GameStatusDTO> history) {
        super(owner, "Mansion Zombie v2 - Histórico", true);
        this.allHistory = history;
        setupWindow();
        setupListeners();
        updateHistoryTable(ALL);
    }

    private void setupWindow() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10));
        this.setSize(550, 350);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterSelector = new JComboBox<>(new String[]{ALL, WINS, LOSSES});
        filterPanel.add(new JLabel("Filtrar resultados:"));
        filterPanel.add(filterSelector);

        this.add(filterPanel, BorderLayout.NORTH);

        tableContainer = new JPanel(new BorderLayout());
        this.add(tableContainer, BorderLayout.CENTER);

        this.setLocationRelativeTo(getOwner());
    }

    private void setupListeners() {
        filterSelector.addActionListener(e -> {
            String selectedFilter = (String) filterSelector.getSelectedItem();
            updateHistoryTable(selectedFilter);
        });
    }

    private void updateHistoryTable(String filter) {

        List<GameStatusDTO> filteredHistory = filterHistory(filter);

        tableContainer.removeAll();

        if (filteredHistory.isEmpty()) {
            JLabel emptyLabel = new JLabel("No hay partidas con el filtro seleccionado (" + filter + ").", SwingConstants.CENTER);
            tableContainer.add(emptyLabel, BorderLayout.CENTER);
        } else {
            Object[][] tableData = convertToTableData(filteredHistory);

            DefaultTableModel model = new DefaultTableModel(tableData, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            JTable historyTable = new JTable(model);
            historyTable.setFillsViewportHeight(true);
            historyTable.getTableHeader().setReorderingAllowed(false);

            JScrollPane scrollPane = new JScrollPane(historyTable);
            tableContainer.add(scrollPane, BorderLayout.CENTER);
        }

        tableContainer.revalidate();
        tableContainer.repaint();
    }

    private List<GameStatusDTO> filterHistory(String filter) {
        return allHistory.stream()
                .filter(dto -> {
                    boolean isDead = dto.playerHp() <= 0;
                    boolean isFinalRoom = dto.currentRoomNumber() == dto.maxRoomNumber();

                    if (filter.equals(WINS)) {
                        return isFinalRoom && !isDead;
                    }
                    if (filter.equals(LOSSES)) {
                        return isDead;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    private Object[][] convertToTableData(List<GameStatusDTO> history) {
        Object[][] data = new Object[history.size()][columnNames.length];

        for (int i = 0; i < history.size(); i++) {
            GameStatusDTO dto = history.get(i);

            boolean isDead = dto.playerHp() <= 0;
            boolean isFinalRoom = dto.currentRoomNumber() == dto.maxRoomNumber();

            String outcome;
            if (isDead) {
                outcome = "DERROTA";
            } else if (isFinalRoom) {
                outcome = "VICTORIA";
            } else {
                outcome = "INCOMPLETO";
            }

            String difficultyName = (dto.maxRoomNumber() == 5) ? "FÁCIL" : "MEDIO";

            data[i][0] = outcome;
            data[i][1] = difficultyName;
            data[i][2] = String.format("%d", dto.currentRoomNumber());
            data[i][3] = dto.playerHp();
            data[i][4] = dto.playerHasKit() ? "Sí" : "No";
            data[i][5] = dto.playerNumberWeapons();
            data[i][6] = dto.playerNumberProtections();
        }

        return data;
    }

    public void showView() {
        this.setVisible(true);
    }
}