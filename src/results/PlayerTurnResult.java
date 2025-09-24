package results;

public class PlayerTurnResult extends ActionResult {
    private final int damage;
    private final int hp;
    public PlayerTurnResult(int damage, int hp) {
        this.damage = damage;
        this.hp = hp;
    }

    @Override
    public ActionResultType getType() {
        return ActionResultType.PLAYER_TURN;
    }

    public int getDamage() {
        return damage;
    }

    public int getHp() {
        return hp;
    }
}
