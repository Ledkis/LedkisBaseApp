package ledkis.ledkisbaseapp.ui.fragments;

import android.os.Bundle;
import android.view.MotionEvent;

import javax.inject.Inject;

import ledkis.ledkisbaseapp.LedkisBaseApplication;
import ledkis.ledkisbaseapp.core.AndroidBus;
import ledkis.ledkisbaseapp.events.ChangeScreenEvent;
import ledkis.ledkisbaseapp.events.ShowcaseEvent;

public abstract class MainScreenBaseFragment extends BaseFragment {

    public static final String TAG = "MainScreenBaseFragment";

    @Inject AndroidBus bus;

    protected String screenName;

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
        showcase();
    }

    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LedkisBaseApplication.get(getActivity()).inject(this);
    }

    public void dispatchTouchEvent(MotionEvent ev) {
    }

    public String getScreenName() {
        return screenName;
    }

    public void onFocus(){
        bus.post(new ChangeScreenEvent(screenName));
    }

    public abstract void showcase();

}
