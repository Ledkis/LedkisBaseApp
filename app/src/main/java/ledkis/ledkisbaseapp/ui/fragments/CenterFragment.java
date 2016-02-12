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
import ledkis.ledkisbaseapp.events.RequestIntroEvent;


public class CenterFragment extends Fragment {

    public static final String TAG = "CenterFragment";

    @Inject AndroidBus bus;

    View focusView;

    public CenterFragment() {
    }

    public static CenterFragment newInstance() {
        CenterFragment f = new CenterFragment();
        return f;
    }

    @Override
    public void onResume() {
        super.onResume();

        bus.register(this);

        bus.post(new RequestIntroEvent(focusView));

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_center, container, false);

        focusView = rootView.findViewById(R.id.focusView);

        return rootView;

    }


}
