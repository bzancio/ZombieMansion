package events;

public class DefaultEventInfo extends GameNotification {

    private final NotificationType type;

    public DefaultEventInfo(NotificationType type) {
        this.type = type;
    }

    @Override
    public NotificationType getType() {
        return type;
    }
}