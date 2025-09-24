package game;

public class Player extends Entity {
    private final int maxHp;
    private int numberWeapons;
    private int numberProtections;
    private boolean hasKit;

    public Player() {
        this.maxHp = 20;
        this.hp = 20;
        this.attackPoints = 4;
        this.numberWeapons = 0;
        this.numberProtections = 0;
        this.hasKit = false;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getNumberWeapons() {
        return numberWeapons;
    }
    public void setNumberWeapons(int numberWeapons) {
        this.numberWeapons = numberWeapons;
    }

    public int getNumberProtections() {
        return numberProtections;
    }
    public void setNumberProtections(int numberProtections) {
        this.numberProtections = numberProtections;
    }

    public boolean getHasKit() {
        return hasKit;
    }

    public void setHasKit(boolean hasKit) {
        this.hasKit = hasKit;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void useKit() {
        hp += 4;
        if (hp > maxHp)
            hp = maxHp;
        hasKit = false;
    }
}