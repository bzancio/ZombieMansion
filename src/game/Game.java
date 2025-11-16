package game;

import actions.*;
import events.GameNotification;
import state.GameStatusDTO;
import ui.ViewController;

import java.util.List;

public class Game {
    private final Player player;
    private final Difficulty difficulty;
    private final ViewController viewController;
    private Room room;
    private GameState state;

    public Game(ViewController viewController, Difficulty difficulty) {
        this.player = new Player();
        this.difficulty = difficulty;
        this.viewController = viewController;
        this.room = new Room(1);
        this.state = GameState.PLAYING;
    }

    public void start() {
        viewController.handleStatusUpdate(createGameStatusDTO());
    }

    public void performAction(Action action) {
        if (state == GameState.PLAYING) {
            ActionStrategy strategy = ActionFactory.create(action, this);
            List<GameNotification> results = strategy.execute();
            viewController.handleAllNotifications(results);
            processActionResults(results);
            if (state != GameState.PLAYING)
                return;
            viewController.handleStatusUpdate(createGameStatusDTO());
            viewController.handleCombatStatusUpdate(createGameStatusDTO());
        }
    }

    private void processActionResults(List<GameNotification> results) {
        GameNotification lastResult = results.getLast();
        switch (lastResult.getType()) {
            case PLAYER_LOSE -> state = GameState.LOSE;
            case ESCAPED -> state = GameState.WIN;
        }
        if (state != GameState.PLAYING) {
            viewController.handleCombatStatusUpdate(createGameStatusDTO());

            if (state == GameState.LOSE) {
                viewController.prepareCombatViewForLoss();
                return;
            }
            viewController.handleStatusUpdate(createGameStatusDTO());
            viewController.handlePlayerWin();
        }
    }

    public void advanceRoom() {
        room = new Room(room.getRoomNumber() + 1);
    }

    public Player getPlayer() {
        return player;
    }

    public Room getRoom() {
        return room;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    private GameStatusDTO createGameStatusDTO() {
        return GameStatusDTO.buildFrom(this);
    }
}