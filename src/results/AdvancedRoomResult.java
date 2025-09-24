package results;

public class AdvancedRoomResult extends ActionResult {
    private final int roomNumber;

    public AdvancedRoomResult(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public ActionResultType getType() {
        return ActionResultType.ADVANCED_ROOM;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
