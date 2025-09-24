package results;

public class ZombieTurnResult extends ActionResult {
    private final int damage;
    private final int hp;

    public ZombieTurnResult(int damage, int hp) {
        this.damage = damage;
        this.hp = hp;
    }

    @Override
    public ActionResultType getType() {
        return ActionResultType.ZOMBIE_TURN;
    }

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }
}
