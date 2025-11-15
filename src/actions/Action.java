package actions;

public enum Action {
    FIGHT("Luchar"),
    SEARCH("Buscar"),
    ADVANCE("Avanzar"),
    HEAL("Curarse"),
    ESCAPE("Escapar");

    private final String label;

    Action(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
