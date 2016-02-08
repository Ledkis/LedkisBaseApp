package ledkis.ledkisbaseapp.job;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;


public abstract class SyncJob extends Job {
    protected static final String TAG = "SyncJob";

    private String requestType;

    public SyncJob(int priority, String requestType) {
        super(new Params(priority).requireNetwork().persist());
        this.requestType = requestType;
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        final String FUNCTION_TAG = "onRun";

        fetch(requestType);
    }

    @Override
    protected void onCancel() {
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        final String FUNCTION_TAG = "onRun";

        return true;
    }

    public abstract void fetch(String requestType);
}
