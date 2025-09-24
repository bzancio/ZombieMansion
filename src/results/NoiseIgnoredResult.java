package results;

public class NoiseIgnoredResult extends ActionResult {
    @Override
    public ActionResultType getType() {
        return ActionResultType.NOISE_IGNORED;
    }
}
