package results;

public class PlayerLoseResult extends ActionResult {
    @Override
    public ActionResultType getType() {
        return ActionResultType.PLAYER_LOSE;
    }
}
