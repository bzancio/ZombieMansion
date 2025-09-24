package results;

public class ProtectionFoundResult extends ActionResult {
    @Override
    public ActionResultType getType() {
        return ActionResultType.WEAPON_FOUND;
    }
}
