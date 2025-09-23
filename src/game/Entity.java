package game;

public class Entity {
    protected int hp;
    protected int attackPoints;

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public boolean isAlive() {
        return (hp != 0);
    }

    public int getHp() {
        return this.hp;
    }
}
