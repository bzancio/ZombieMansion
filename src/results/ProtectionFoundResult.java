package results;

public class ProtectionFoundResult extends ActionResult {
    @Override
    public ActionResultType getType() {
        return ActionResultType.PROTECTION_FOUND;
    }
}
