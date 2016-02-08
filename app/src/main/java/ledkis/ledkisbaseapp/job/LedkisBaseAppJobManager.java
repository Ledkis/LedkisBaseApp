package ledkis.ledkisbaseapp.job;

import android.content.Context;

import com.path.android.jobqueue.BaseJob;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.di.DependencyInjector;

import ledkis.ledkisbaseapp.LedkisBaseApplication;
import ledkis.ledkisbaseapp.core.Constants;


public class LedkisBaseAppJobManager extends JobManager {

    public LedkisBaseAppJobManager(final Context context) {
        super(context, new Configuration.Builder(context)
                .injector(new DependencyInjector() {
                    @Override
                    public void inject(BaseJob baseJob) {
                        LedkisBaseApplication.get(context).inject(baseJob);
                    }
                })
                .minConsumerCount(Constants.Job.MIN_CONSUMER_COUNT)
                .maxConsumerCount(Constants.Job.MAX_CONSUMER_COUNT)
                .loadFactor(Constants.Job.LOAD_FACTOR)
                .consumerKeepAlive(Constants.Job.CONSUMER_KEEP_ALIVE)
                .build());
    }
}
