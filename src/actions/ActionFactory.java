package actions;

import game.Game;

import java.util.function.BiConsumer;

public class ActionFactory {
    public static ActionStrategy create(Action action, Game game) {
        return switch (action) {
            case HEAL -> new HealAction(game.getPlayer()::useKit);
            case FIGHT -> new FightAction(game);
            case SEARCH -> new SearchAction(
                    game.getRoom()::setRemainingSearchAttempts,
                    game.getPlayer()::setHasKit,
                    game.getPlayer()::setNumberProtections,
                    game.getPlayer()::setNumberWeapons,
                     game.getRoom()::addZombie,
                    game.getRoom()::setActiveZombies,
                    game.getPlayer().getHasKit(),
                    game.getRoom().getRemainingSearchAttempts(),
                    game.getPlayer().getNumberProtections(),
                    game.getPlayer().getNumberWeapons(),
                    game.getRoom().getActiveZombies(),
                    game.getCurrentRoomNumber()
            );
            case ADVANCE -> new AdvanceAction(game::advanceRoom, game.getRoom().getRoomNumber());
            case ESCAPE -> new EscapeAction();
        };
    }
}
