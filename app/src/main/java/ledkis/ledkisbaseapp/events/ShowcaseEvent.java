package ledkis.ledkisbaseapp.events;

import android.app.Activity;
import android.view.View;

import co.mobiwise.materialintro.target.Target;

public class ShowcaseEvent {

    private int showcaseId;
    private int priority;
    private View targetView;
    private boolean showcaseViewCase;
    private String screeName;
    private Activity activity;

    public ShowcaseEvent(int showcaseId, int priority, View targetView, boolean showcaseViewCase, String screeName, Activity activity) {
        this.showcaseId = showcaseId;
        this.priority = priority;
        this.targetView = targetView;
        this.showcaseViewCase = showcaseViewCase;
        this.screeName = screeName;
        this.activity = activity;
    }

    public int getShowcaseId() {
        return showcaseId;
    }

    public int getPriority() {
        return priority;
    }

    public View getTargetView() {
        return targetView;
    }

    public boolean isShowcaseViewCase() {
        return showcaseViewCase;
    }

    public String getScreeName() {
        return screeName;
    }

    public Activity getActivity() {
        return activity;
    }
}
