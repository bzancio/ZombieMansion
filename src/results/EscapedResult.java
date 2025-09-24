package results;

public class EscapedResult extends ActionResult {

    @Override
    public ActionResultType getType() {
        return ActionResultType.ESCAPED;
    }
}
