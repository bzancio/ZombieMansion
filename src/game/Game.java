package game;

import java.io.Serializable;

public class Game implements Serializable {
    private final Player player;
    private final Difficulty difficulty;
    private Room room;
    private GameState state;

    public Game(Difficulty difficulty) {
        this.player = new Player();
        this.difficulty = difficulty;
        this.room = new Room(1);
        this.state = GameState.PLAYING;
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

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
