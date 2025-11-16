package actions;

import java.io.Serializable;

public enum Action implements Serializable {
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
