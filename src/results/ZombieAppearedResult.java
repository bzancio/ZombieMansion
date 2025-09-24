package results;

public class ZombieAppearedResult extends ActionResult {
    private final int zombieNumber;

    public ZombieAppearedResult(int zombieNumber) {
        this.zombieNumber = zombieNumber;
    }

    @Override
    public ActionResultType getType() {
        return ActionResultType.ZOMBIE_APPEARED;
    }
    public int getZombieNumber() {
        return zombieNumber;
    }
}
