package ledkis.ledkisbaseapp.events;

import android.view.View;

/**
 * Created by ledkis on 12/02/2016.
 */
public class RequestIntroEvent {

    private View targetView;

    public RequestIntroEvent(View targetView) {
        this.targetView = targetView;
    }

    public View getTargetView() {
        return targetView;
    }
}
