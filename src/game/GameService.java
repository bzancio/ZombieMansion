package game;

import actions.Action;
import actions.ActionFactory;
import actions.ActionStrategy;
import events.GameNotification;
import state.GameStatusDTO;

import java.util.ArrayList;
import java.util.List;

public class GameService {

    public TurnResult start(Game game) {
        return new TurnResult(new ArrayList<>(), game.getState(), GameStatusDTO.buildFrom(game));
    }

    public TurnResult performAction(Game game, Action action) {
        if (game.getState() != GameState.PLAYING) {
            return new TurnResult(new ArrayList<>(), game.getState(), GameStatusDTO.buildFrom(game));
        }

        ActionStrategy strategy = ActionFactory.create(action, game);
        List<GameNotification> notifications = strategy.execute();
        updateGameState(game, notifications);

        return new TurnResult(notifications, game.getState(), GameStatusDTO.buildFrom(game));
    }

    private void updateGameState(Game game, List<GameNotification> notifications) {
        if (notifications.isEmpty()) {
            return;
        }

        GameNotification lastResult = notifications.getLast();
        switch (lastResult.getType()) {
            case PLAYER_LOSE -> game.setState(GameState.LOSE);
            case ESCAPED -> game.setState(GameState.WIN);
        }
    }
}
