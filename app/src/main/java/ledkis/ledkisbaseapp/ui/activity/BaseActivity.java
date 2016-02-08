package ledkis.ledkisbaseapp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import ledkis.ledkisbaseapp.util.log.Ln;


public abstract class BaseActivity extends Activity {

    public static boolean DEBUG = false;

    protected String screenName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Ln.screenState(screenName, "onCreate", DEBUG);
        // http://stackoverflow.com/questions/4250149/requestfeature-must-be-called-before-adding-content
        initWindow();
        super.onCreate(savedInstanceState);
//        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    public void initWindow() {

    }


    @Override
    protected void onStart() {
        Ln.screenState(screenName, "onStart", DEBUG);
        super.onStart();
    }

    @Override
    protected void onResume() {
        Ln.screenState(screenName, "onResume", DEBUG);
        super.onResume();
    }

    @Override
    protected void onPause() {
        Ln.screenState(screenName, "onPause", DEBUG);
        super.onPause();
    }

    @Override
    protected void onStop() {
        Ln.screenState(screenName, "onStop", DEBUG);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Ln.screenState(screenName, "onDestroy", DEBUG);
//        Analytics.flush(this);
        super.onDestroy();
    }


    @Override
    protected void onRestart() {
        Ln.screenState(screenName, "onRestart", DEBUG);
        super.onRestart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Ln.screenState(screenName, "onSaveInstanceState", DEBUG);
        super.onSaveInstanceState(outState);
//        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Ln.screenState(screenName, "onRestoreInstanceState", DEBUG);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        Ln.screenState(screenName, "onRestoreInstanceState", DEBUG);
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Ln.screenState(screenName, "onActivityResult", DEBUG);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getScreenName() {
        return screenName;
    }
}
