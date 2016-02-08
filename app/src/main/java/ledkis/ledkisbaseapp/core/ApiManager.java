package ledkis.ledkisbaseapp.core;

import android.content.Context;

import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import ledkis.ledkisbaseapp.LedkisBaseApplication;
import ledkis.ledkisbaseapp.job.LedkisBaseAppJobManager;
import ledkis.ledkisbaseapp.job.SyncUserJob;

public class ApiManager {

    public static final String TAG = "UpdateManager";

    @Inject AndroidBus bus;
    @Inject LedkisBaseAppJobManager ledkisBaseAppJobManager;

    private Context context;

    public ApiManager(Context context) {
        this.context = context;
        LedkisBaseApplication.get(context).inject(this);
    }

    public void initBus() {
        bus.register(this);
    }

    public void fetch(String requestType) {
        ledkisBaseAppJobManager.addJob(new SyncUserJob(Constants.Job.PRIORITY_FETCH, requestType));
    }

    public void update(String requestType) {
    }


}
