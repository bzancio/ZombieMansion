package game;

import events.GameNotification;
import state.GameStatusDTO;

import java.util.List;

public record TurnResult(List<GameNotification> notifications, GameState state, GameStatusDTO status) {
    public boolean isFinished() {
        return state != GameState.PLAYING;
    }
}
