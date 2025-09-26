package actions;

import game.Game;

public class ActionFactory {
    public static ActionStrategy create(Action action, Game game) {
        return switch (action) {
            case HEAL -> new HealAction(game.getPlayer());
            case FIGHT -> new FightAction(game);
            case SEARCH -> new SearchAction(game);
            case ADVANCE -> new AdvanceAction(game);
            case ESCAPE -> new EscapeAction();
        };
    }
}
