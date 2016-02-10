package ledkis.ledkisbaseapp.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ledkis.ledkisbaseapp.R;
import ledkis.ledkisbaseapp.ui.adapters.MainPagerAdapter;
import ledkis.ledkisbaseapp.ui.views.ControlSwipeViewPager;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment {

    private MainPagerAdapter pagerAdapter;
    private ControlSwipeViewPager viewPager;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        viewPager = (ControlSwipeViewPager) rootView.findViewById(R.id.mainScreenPager);

        pagerAdapter = new MainPagerAdapter(getActivity(), getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(MainPagerAdapter.CENTER_POSITION);
        viewPager.setPagingEnabled(true);
        viewPager.setOffscreenPageLimit(2);

        return rootView;
    }
}
