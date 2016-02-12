package ledkis.ledkisbaseapp.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ledkis.ledkisbaseapp.R;
import ledkis.ledkisbaseapp.ui.adapters.MainPagerAdapter;
import ledkis.ledkisbaseapp.ui.views.ControlSwipeViewPager;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment implements ViewPager.OnPageChangeListener{

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
        viewPager.setOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(2);

        return rootView;
    }

    @Override
    public void onPageSelected(int position) {
        pagerAdapter.getItem(position).onFocus();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
