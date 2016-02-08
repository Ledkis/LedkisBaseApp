package ledkis.ledkisbaseapp.job;

import javax.inject.Inject;

import ledkis.ledkisbaseapp.data.api.UserApi;


public class SyncUserJob extends SyncJob {
    protected static final String TAG = "SyncUserJob";

    @Inject transient UserApi userApi;

    public SyncUserJob(int priority, String requestType) {
        super(priority, requestType);
    }

    @Override
    public void fetch(String requestType) {
        userApi.fetchUser(requestType);
    }
}
