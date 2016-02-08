package ledkis.ledkisbaseapp.util;

public class NetworkStatus {

    private final boolean connected;

    public NetworkStatus(boolean connected) {
        this.connected = connected;
    }

    public boolean isConnected() {
        return connected;
    }

}
