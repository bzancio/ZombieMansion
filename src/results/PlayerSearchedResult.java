package results;

public class PlayerSearchedResult extends ActionResult {
    @Override
    public ActionResultType getType() {
        return ActionResultType.PLAYER_SEARCHED;
    }
}
