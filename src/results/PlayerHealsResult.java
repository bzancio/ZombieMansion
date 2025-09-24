package results;

public class PlayerHealsResult extends ActionResult {
    @Override
    public ActionResultType getType() {
        return ActionResultType.PLAYER_HEALS;
    }
}
