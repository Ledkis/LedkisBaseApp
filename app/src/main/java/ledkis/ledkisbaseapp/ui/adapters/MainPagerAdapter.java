package ledkis.ledkisbaseapp.ui.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import ledkis.ledkisbaseapp.R;
import ledkis.ledkisbaseapp.ui.fragments.CenterFragment;
import ledkis.ledkisbaseapp.ui.fragments.LeftFragment;
import ledkis.ledkisbaseapp.ui.fragments.MainScreenBaseFragment;
import ledkis.ledkisbaseapp.ui.fragments.RightFragment;


/**
 * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
/*
* http://alexfu.github.io/blog/2013/04/22/retaining-fragments-in-fragmentpageradapter/
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    public static final int CENTER_POSITION = 1;
    public static final int RIGHT_POSITION = 2;
    public static final int LEFT_POSITION = 0;

    private FragmentManager fragmentManager;
    private Context context;

    public MainPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm;
        this.context = context;
    }

    @Override
    public MainScreenBaseFragment getItem(int position) {
        // Check if this Fragment already exists.
        String name = makeFragmentName(R.id.mainScreenPager, position);
        MainScreenBaseFragment f = (MainScreenBaseFragment) fragmentManager.findFragmentByTag(name);
        if (f == null) {
            switch (position) {
                case LEFT_POSITION:
                    f = LeftFragment.newInstance();
                    break;
                case CENTER_POSITION:
                    f = CenterFragment.newInstance();
                    break;
                case RIGHT_POSITION:
                    f = RightFragment.newInstance();
                    break;
                default:
                    break;
            }
        }

        return f;

    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public int getCount() {
        return 3;
    }

    private static String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }


}
