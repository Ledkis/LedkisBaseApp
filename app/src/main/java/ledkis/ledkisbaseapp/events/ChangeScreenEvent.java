package ledkis.ledkisbaseapp.events;

public class ChangeScreenEvent {

    private String screenName;

    public ChangeScreenEvent(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenName() {
        return screenName;
    }
}
