package actions;

import events.GameNotification;

import java.util.List;

public interface ActionStrategy {
    List<GameNotification> execute();
}
