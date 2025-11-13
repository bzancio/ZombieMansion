package game;

import actions.*;
import results.ActionResult;
import results.GameStatusResult;
import ui.UIController;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Player player;
    private int currentRoomNumber;
    private Room currentRoom;
    private final Difficulty difficulty;
    private GameState state;
    private final UIController uiController;

    public Game(UIController uiController, Difficulty difficulty) {
        this.uiController = uiController;
        this.difficulty = difficulty;
        this.currentRoomNumber = 1;
        this.currentRoom = new Room(currentRoomNumber);
        this.player = new Player();
        this.state = GameState.PLAYING;
    }

    public void start() {
        uiController.presentResult(new GameStatusResult(this));
    }

    public void performAction(Action action) {
        if (state == GameState.PLAYING) {
            ActionStrategy strategy = ActionFactory.create(action, this);
            List<ActionResult> results = strategy.execute();
            uiController.presentAllResults(results);
            processActionResults(results);
            uiController.presentResult(new GameStatusResult(this));
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

    private void processActionResults(List<ActionResult> results) {
        ActionResult lastResult = results.getLast();
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
}