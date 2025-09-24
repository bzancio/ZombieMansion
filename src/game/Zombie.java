package game;

import java.util.concurrent.ThreadLocalRandom;

public class Zombie extends Entity{

    public Zombie(int currentRoom) {
        int extra = ThreadLocalRandom.current().nextInt(0,2);
        this.hp = 2 + (currentRoom - 1) + extra;
        extra = ThreadLocalRandom.current().nextInt(0,2);
        this.attackPoints = 2 + (currentRoom - 1) + extra;
    }

    public int getAttackPoints() {
        return attackPoints;
    }
}