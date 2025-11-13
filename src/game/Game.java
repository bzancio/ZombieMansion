package game;

import actions.*;
import events.GameNotification;
import state.GameStatusDTO;
import ui.ViewController;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Player player;
    private int currentRoomNumber;
    private Room currentRoom;
    private final Difficulty difficulty;
    private GameState state;
    private final ViewController viewController;

    public Game(ViewController viewController, Difficulty difficulty) {
        this.viewController = viewController;
        this.difficulty = difficulty;
        this.currentRoomNumber = 1;
        this.currentRoom = new Room(currentRoomNumber);
        this.player = new Player();
        this.state = GameState.PLAYING;
    }

    public void start() {
        viewController.handleNotification(createGameStatusDTO());
    }

    public void performAction(Action action) {
        if (state == GameState.PLAYING) {
            ActionStrategy strategy = ActionFactory.create(action, this);
            List<GameNotification> results = strategy.execute();
            viewController.handleAllNotifications(results);
            processActionResults(results);
            viewController.handleNotification(createGameStatusDTO());
        }
    }

    public List<Action> getAvailableActions() {
        List<Action> actions = new ArrayList<>();
        if (FightAction.isAvailable(this))
            actions.add(Action.FIGHT);
        else {
            if (SearchAction.isAvailable(this))
                actions.add(Action.SEARCH);
            if (HealAction.isAvailable(this))
                actions.add(Action.HEAL);
            if (AdvanceAction.isAvailable(this))
                actions.add(Action.ADVANCE);
            if (EscapeAction.isAvailable(this))
                actions.add(Action.ESCAPE);
        }
        return actions;
    }

    private void processActionResults(List<GameNotification> results) {
        GameNotification lastResult = results.getLast();
        switch (lastResult.getType()) {
            case PLAYER_LOSE -> state = GameState.LOSE;
            case ESCAPED -> state = GameState.WIN;
        }
    }

    public void advanceRoom() {
        currentRoomNumber++;
        currentRoom = new Room(currentRoomNumber);
    }

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getCurrentRoomNumber() {
        return currentRoomNumber;
    }

    private GameStatusDTO createGameStatusDTO() {
        return new GameStatusDTO(
                currentRoomNumber,
                player.hp,
                player.getMaxHp(),
                player.getAttackPoints(),
                player.getNumberWeapons(),
                player.getNumberProtections(),
                player.getHasKit(),
                currentRoom.getActiveZombies(),
                currentRoom.getRemainingSearchAttempts(),
                difficulty.getRoomNumber(),
                getAvailableActions()
        );
    }
}