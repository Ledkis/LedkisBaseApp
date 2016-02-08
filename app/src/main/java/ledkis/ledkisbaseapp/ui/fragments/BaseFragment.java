package ledkis.ledkisbaseapp.ui.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ledkis.ledkisbaseapp.util.log.Ln;

public abstract class BaseFragment extends Fragment {

    private static boolean DEBUG = false;

    // TODO improve : fragmentName and screenName in OnBoardingBaseFragment or MainScreenBaseFragment
    // Different from screenName we can find in OnBoardingBaseFragment or MainScreenBaseFragment that is used in Analytics case. fragmentName is used for logging case ( fragmentName = "" doesn't enable the logging)
    protected String fragmentName = "";

    public static interface FragmentLifecycleListener {
        public void onFragmentResumed(Fragment fragment);
    }

    FragmentLifecycleListener listener;

    @Override
    public void onAttach(Activity activity) {
        Ln.screenState(fragmentName, "onAttach", DEBUG);
        super.onAttach(activity);
        try {
            if (activity instanceof FragmentLifecycleListener) {
                listener = (FragmentLifecycleListener) activity;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Ln.screenState(fragmentName, "onCreate", DEBUG);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Ln.screenState(fragmentName, "onCreateView", DEBUG);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Ln.screenState(fragmentName, "onViewCreated", DEBUG);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Ln.screenState(fragmentName, "onActivityCreated", DEBUG);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Ln.screenState(fragmentName, "onStart", DEBUG);
        super.onStart();
    }

    @Override
    public void onResume() {
        Ln.screenState(fragmentName, "onResume", DEBUG);
        super.onResume();
        if (listener != null) {
            listener.onFragmentResumed(this);
        }
    }

    @Override
    public void onPause() {
        Ln.screenState(fragmentName, "onPause", DEBUG);
        super.onPause();
    }

    @Override
    public void onStop() {
        Ln.screenState(fragmentName, "onStop", DEBUG);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Ln.screenState(fragmentName, "onDestroyView", DEBUG);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Ln.screenState(fragmentName, "onDestroy", DEBUG);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Ln.screenState(fragmentName, "onDetach", DEBUG);
        super.onDetach();
    }


    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        Ln.screenState(fragmentName, "onViewStateRestored", DEBUG);
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Ln.screenState(fragmentName, "onSaveInstanceState", DEBUG);
        super.onSaveInstanceState(outState);
    }

    public String getFragmentName() {
        return fragmentName;
    }
}
