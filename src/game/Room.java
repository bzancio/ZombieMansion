package game;

import java.util.ArrayList;

public class Room {
    private int remainingSearchAttemps;
    private int activeZombies;

    private ArrayList<Zombie> zombieArray;

    public Room(int currentRoom) {
        this.zombieArray = new ArrayList<>();
        this.remainingSearchAttemps = 3;
        this.activeZombies = 1;
        Zombie z = new Zombie(currentRoom);
        zombieArray.add(z);
    }

    public int getRemainingSearchAttemps() {
        return remainingSearchAttemps;
    }
    public void setRemainingSearchAttemps(int remainingFindAttemps) {
        this.remainingSearchAttemps = remainingFindAttemps;
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