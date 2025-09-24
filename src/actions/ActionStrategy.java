package actions;

import results.ActionResult;

import java.util.List;

public interface ActionStrategy {
    List<ActionResult> execute();
}
