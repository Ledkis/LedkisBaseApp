package ledkis.ledkisbaseapp;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;
import com.squareup.otto.Produce;

import net.danlew.android.joda.JodaTimeAndroid;

import javax.inject.Inject;

import dagger.ObjectGraph;
import ledkis.ledkisbaseapp.core.AndroidBus;
import ledkis.ledkisbaseapp.core.ApiManager;
import ledkis.ledkisbaseapp.core.ShowcaseManager;
import ledkis.ledkisbaseapp.util.NetworkStatus;
import ledkis.ledkisbaseapp.util.NetworkUtils;
import ledkis.ledkisbaseapp.util.log.Ln;

public class LedkisBaseApplication extends Application {
    public static final String TAG = "LedkisBaseApplication";

    @Inject AndroidBus bus;
    @Inject ApiManager apiManager;
    @Inject ShowcaseManager showcaseManager;

    private NetworkStatus networkStatus;

    private ObjectGraph objectGraph;

    public static LedkisBaseApplication get(Context context) {
        return (LedkisBaseApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Perform injection
        buildObjectGraphAndInject();

        //Init joda time
        JodaTimeAndroid.init(this);

        initLogger();
//        initBackend();

        apiManager.initBus();
        showcaseManager.initBus();

        updateNetworkStatus();

        bus.register(this);
    }

    public void buildObjectGraphAndInject() {
        objectGraph = ObjectGraph.create(Modules.list(this));
        objectGraph.inject(this);
    }

    public void inject(Object target) {
        objectGraph.inject(target);
    }

    private void initLogger() {
        Ln.init(this);
    }

    private void initBackend() {

        //Parse init
        Parse.initialize(this, BuildConfig.PARSE_APP_ID, BuildConfig.PARSE_CLIENT_ID);

//        ParseACL defaultACL = new ParseACL();
//        ParseACL.setDefaultACL(defaultACL, true);

        ParseInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                } else {
                }
            }
        });
    }

    @Produce
    public NetworkStatus produceNetworkStatus() {
        return networkStatus;
    }

    public NetworkStatus getNetworkStatus() {
        return networkStatus;
    }

    public void updateNetworkStatus() {
        boolean isConnected = NetworkUtils.isOn(this);
        networkStatus = new NetworkStatus(isConnected);

        bus.post(produceNetworkStatus());

    }
}
