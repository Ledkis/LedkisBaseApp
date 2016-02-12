package ledkis.ledkisbaseapp.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ledkis.ledkisbaseapp.R;
import ledkis.ledkisbaseapp.events.ShowcaseEvent;

public class RightFragment extends MainScreenBaseFragment {

    public static final String TAG = "RightFragment";

    View showcase1TextView;
    View showcase2TextView;
    View showcase3TextView;

    public RightFragment() {
        screenName = "RightFragment";
    }

    public static RightFragment newInstance() {
        RightFragment f = new RightFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_right, container, false);

        showcase1TextView = rootView.findViewById(R.id.showcase1TextView);
        showcase2TextView = rootView.findViewById(R.id.showcase2TextView);
        showcase3TextView = rootView.findViewById(R.id.showcase3TextView);

        return rootView;

    }

    @Override
    public void showcase() {
        bus.post(new ShowcaseEvent(7, 3, showcase1TextView, false, screenName, getActivity()));
        bus.post(new ShowcaseEvent(8, 2, showcase2TextView, false, screenName, getActivity()));
        bus.post(new ShowcaseEvent(9, 1, showcase3TextView, false, screenName, getActivity()));
    }

}
