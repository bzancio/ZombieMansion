package game;

public enum Difficulty {
    EASY(5, "Facil (5 Habitaciones)"),
    MEDIUM(7, "Medio (7 Habitaciones)"),
    HARD(10, "Difícil (10 habitaciones)");

    private final int maxRoom;
    private final String label;

    Difficulty(int maxRoom, String label) {
        this.maxRoom = maxRoom;
        this.label = label;
    }

    public int getRoomNumber() {
        return maxRoom;
    }

    @Override
    public String toString() {
        return label;
    }
}