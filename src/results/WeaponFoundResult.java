package results;

public class WeaponFoundResult extends ActionResult {
    @Override
    public ActionResultType getType() {
        return ActionResultType.WEAPON_FOUND;
    }
}
