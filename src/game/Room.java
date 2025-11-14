package game;

import java.util.ArrayList;

public class Room {
    private int remainingSearchAttempts;
    private int activeZombies;
    private int roomNumber;

    private final ArrayList<Zombie> zombieArray;

    public Room(int currentRoom) {
        this.roomNumber = currentRoom;
        this.zombieArray = new ArrayList<>();
        this.remainingSearchAttempts = 3;
        this.activeZombies = 1;
        Zombie z = new Zombie(roomNumber);
        zombieArray.add(z);
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getRemainingSearchAttempts() {
        return remainingSearchAttempts;
    }
    public void setRemainingSearchAttempts(int remainingSearchAttempts) {
        this.remainingSearchAttempts = remainingSearchAttempts;
    }

    public int getActiveZombies() {
        return activeZombies;
    }
    public void setActiveZombies(int activeZombies) {
        this.activeZombies = activeZombies;
    }

    public ArrayList<Zombie> getZombieArray() {
        return zombieArray;
    }

    public void deleteZombie() {
        zombieArray.removeFirst();
    }

    public void addZombie(int currentRoom) {
        Zombie z = new Zombie(currentRoom);
        zombieArray.add(z);
    }

    public boolean hasActiveZombies() {
        return activeZombies > 0;
    }
}