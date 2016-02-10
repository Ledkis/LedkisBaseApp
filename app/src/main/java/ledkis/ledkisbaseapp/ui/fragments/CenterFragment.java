package ledkis.ledkisbaseapp.ui.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import ledkis.ledkisbaseapp.R;


public class CenterFragment extends Fragment {

    public static final String TAG = "CenterFragment";

    public CenterFragment() {
    }

    public static CenterFragment newInstance() {
        CenterFragment f = new CenterFragment();
        return f;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_center, container, false);


        return rootView;

    }


}
