package ledkis.ledkisbaseapp.job;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
        },
        complete = false,
        library = true
)
public class JobModule {
    @Provides
    @Singleton
    LedkisBaseAppJobManager provideShouldIJobManager(final Context context) {
        return new LedkisBaseAppJobManager(context);
    }
}
