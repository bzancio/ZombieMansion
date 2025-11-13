package events;

public class RoomAdvanceInfo extends GameNotification {
    private final int roomNumber;

    public RoomAdvanceInfo(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public NotificationType getType() {
        return NotificationType.ADVANCED_ROOM;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
