package ledkis.ledkisbaseapp.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.mobiwise.materialintro.view.MaterialIntroView;
import ledkis.ledkisbaseapp.R;
import ledkis.ledkisbaseapp.events.ShowcaseEvent;

public class LeftFragment extends MainScreenBaseFragment {

    public static final String TAG = "LeftFragment";

    View showcase1TextView;
    View showcase2TextView;
    View showcase3TextView;

    public LeftFragment() {
        screenName = "LeftFragment";
    }

    public static LeftFragment newInstance() {
        LeftFragment f = new LeftFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_left, container, false);

        showcase1TextView = rootView.findViewById(R.id.showcase1TextView);
        showcase2TextView = rootView.findViewById(R.id.showcase2TextView);
        showcase3TextView = rootView.findViewById(R.id.showcase3TextView);

        return rootView;

    }

    @Override
    public void showcase() {
        bus.post(new ShowcaseEvent(4, 1, showcase1TextView, false, screenName, getActivity()));
        bus.post(new ShowcaseEvent(5, 3, showcase2TextView, false, screenName, getActivity()));
        bus.post(new ShowcaseEvent(6, 2, showcase3TextView, false, screenName, getActivity()));
    }

}
