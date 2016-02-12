package ledkis.ledkisbaseapp.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.view.MaterialIntroView;
import ledkis.ledkisbaseapp.LedkisBaseApplication;
import ledkis.ledkisbaseapp.R;
import ledkis.ledkisbaseapp.core.AndroidBus;
import ledkis.ledkisbaseapp.events.ShowcaseEvent;


public class CenterFragment extends MainScreenBaseFragment {

    public static final String TAG = "CenterFragment";

    View showcase1TextView;
    View showcase2TextView;
    View showcase3TextView;

    public CenterFragment() {
        screenName = "CenterFragment";
    }

    public static CenterFragment newInstance() {
        CenterFragment f = new CenterFragment();
        return f;
    }

    @Override
    public void showcase() {
        View showcaseView = MaterialIntroView.getCenterShowcaseView(getActivity(), R.layout.showcase_main);
        bus.post(new ShowcaseEvent(1, 1, showcaseView, true, screenName, getActivity()));

        bus.post(new ShowcaseEvent(1, 2, showcase1TextView, false, screenName, getActivity()));
        bus.post(new ShowcaseEvent(2, 3, showcase2TextView, false, screenName, getActivity()));
        bus.post(new ShowcaseEvent(3, 4, showcase3TextView, false, screenName, getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_center, container, false);

        showcase1TextView = rootView.findViewById(R.id.showcase1TextView);
        showcase2TextView = rootView.findViewById(R.id.showcase2TextView);
        showcase3TextView = rootView.findViewById(R.id.showcase3TextView);

        return rootView;

    }

}
