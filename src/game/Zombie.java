package game;

import java.util.concurrent.ThreadLocalRandom;

public class Zombie extends Entity{

    public Zombie(int currentRoom) {
        this.hp = (int)(ThreadLocalRandom.current().nextFloat(0,1) + 2 + (currentRoom - 1));
        this.attackPoints = (int)(ThreadLocalRandom.current().nextFloat(0,1) + 2 + (currentRoom - 1));
    }

    public int getAttackPoints() {
        return attackPoints;
    }
}