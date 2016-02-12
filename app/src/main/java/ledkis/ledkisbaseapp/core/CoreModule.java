package ledkis.ledkisbaseapp.core;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ledkis.ledkisbaseapp.job.LedkisBaseAppJobManager;

@Module(
        injects = {
                ApiManager.class,
        },
        complete = false,

        library = true
)
public class CoreModule {

    @Singleton
    @Provides
    AndroidBus provideOttoBus() {
        return new AndroidBus();
    }


    @Singleton
    @Provides
    ApiManager provideApiManager(final Context context) {
        return new ApiManager(context);
    }

}
