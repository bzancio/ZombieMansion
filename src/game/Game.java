package game;

import actions.*;
import actions.ActionResult;
import ui.ConsoleUI;
import ui.UIController;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Player player;
    private int currentRoomNumber;
    private Room currentRoom;
    private final Difficulty difficulty;
    private GameState state;
    private final ConsoleUI consoleUI;
    private final UIController uiController;

    public Game(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
        this.uiController = new UIController(consoleUI);
        this.currentRoomNumber = 1;
        this.currentRoom = new Room(currentRoomNumber);
        this.difficulty = consoleUI.askDifficulties(List.of(Difficulty.EASY, Difficulty.HARD));
        this.player = new Player();
        this.state = GameState.PLAYING;
    }

    public void loop() {
        consoleUI.showTitle();
        while (state == GameState.PLAYING) {
            uiController.showTurnStatus(this);
            List<Action> actions = getAvailableActions();
            Action action = consoleUI.askActions(actions);
            ActionStrategy strategy = ActionFactory.create(action, this);
            List<ActionResult> results = strategy.execute();
            uiController.presentAllResults(results);
            processActionResults(results);
        }
    }

    private List<Action> getAvailableActions() {
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
        if (results.getLast().type() == ActionResult.ActionResultType.PLAYER_LOSE)
            state = GameState.LOSE;
        if (results.getLast().type() == ActionResult.ActionResultType.ESCAPED)
            state = GameState.WIN;
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