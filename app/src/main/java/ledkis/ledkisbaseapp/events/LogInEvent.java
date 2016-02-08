package ledkis.ledkisbaseapp.events;

public class LogInEvent {

    private boolean isNew;

    public LogInEvent(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean isNew() {
        return isNew;
    }

}
