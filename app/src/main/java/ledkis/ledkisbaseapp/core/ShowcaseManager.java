package ledkis.ledkisbaseapp.core;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import co.mobiwise.materialintro.animation.MaterialIntroListener;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.utils.*;
import co.mobiwise.materialintro.utils.Constants;
import co.mobiwise.materialintro.view.MaterialIntroView;
import ledkis.ledkisbaseapp.LedkisBaseApplication;
import ledkis.ledkisbaseapp.R;
import ledkis.ledkisbaseapp.events.ChangeScreenEvent;
import ledkis.ledkisbaseapp.events.ShowcaseEvent;
import ledkis.ledkisbaseapp.util.log.Ln;

public class ShowcaseManager {
    public static final String TAG = "ShowcaseManager";

    @Inject AndroidBus bus;

    private Context context;

    private TimeBuffer timeBuffer;

    private String currentScreenName;

    private List<ShowcaseEvent> showcaseEvents;

    public ShowcaseManager(Context context) {
        this.context = context;
        LedkisBaseApplication.get(context).inject(this);

        showcaseEvents = new ArrayList<>();
        timeBuffer = new TimeBuffer();

        currentScreenName = "CenterFragment";
    }

    public void initBus() {
        bus.register(this);
    }


    private void displayShowcase(long fadeInAnimationDuration) {
        if (0 != showcaseEvents.size()) {

            Collections.sort(showcaseEvents, new Comparator<ShowcaseEvent>() {
                @Override
                public int compare(final ShowcaseEvent showcase1, final ShowcaseEvent showcase2) {
                    return showcase1.getPriority() - showcase2.getPriority();
                }
            });

            for (ShowcaseEvent showcaseEvent : showcaseEvents) {
                if (currentScreenName.equals(showcaseEvent.getScreeName())) {

                    final ShowcaseEvent currentShowcaseEvent = showcaseEvent;

                    new MaterialIntroView.Builder(currentShowcaseEvent.getActivity())
                            .enableDotAnimation(true)
                            .setFocusGravity(FocusGravity.CENTER)
                            .setFocusType(Focus.MINIMUM)
                            .setDelayMillis(0)
                            .setFadeInAnimationDuration(fadeInAnimationDuration)
                            .setFadeOutAnimationDuration(Constants.DEFAULT_FADE_DURATION/2)
                            .enableFadeAnimation(true)
                            .performClick(true)
                            .setInfoText("id: " + currentShowcaseEvent.getShowcaseId())
                            .setTarget(currentShowcaseEvent.getTargetView())
                            .setUsageId(System.currentTimeMillis() + "") //THIS SHOULD BE UNIQUE ID
                            .setShowcaseViewCase(currentShowcaseEvent.isShowcaseViewCase())
                            .setListener(new MaterialIntroListener() {
                                @Override
                                public void onUserClicked(String materialIntroViewId) {
                                    showcaseEvents.remove(currentShowcaseEvent);
                                    displayShowcase(Constants.DEFAULT_FADE_DURATION/2);
                                }
                            })
                            .show();

                    break;
                }
            }
        }
    }

    @Subscribe
    public void onShowcaseEvent(ShowcaseEvent event) {
        showcaseEvents.add(event);
        timeBuffer.start(1000, new Runnable() {
            @Override
            public void run() {
                displayShowcase(Constants.DEFAULT_FADE_DURATION);
            }
        });
        Ln.d(TAG, "ShowcaseEvent");
    }

    @Subscribe
    public void onChangeScreenEvent(ChangeScreenEvent event) {
        currentScreenName = event.getScreenName();
        timeBuffer.start(1000, new Runnable() {
            @Override
            public void run() {
                displayShowcase(Constants.DEFAULT_FADE_DURATION);
            }
        });
        Ln.d(TAG, "ChangeScreenEvent");
    }


}
