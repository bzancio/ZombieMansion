package actions;

public enum Action {
    FIGHT("Luchar"),
    SEARCH("Buscar"),
    ADVANCE("Avanzar"),
    ESCAPE("Escapar"),
    HEAL("Curarse");

    private final String label;

    Action(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
