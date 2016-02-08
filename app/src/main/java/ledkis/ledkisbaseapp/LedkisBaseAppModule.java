package ledkis.ledkisbaseapp;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ledkis.ledkisbaseapp.core.CoreModule;
import ledkis.ledkisbaseapp.data.DataModule;
import ledkis.ledkisbaseapp.job.JobModule;
import ledkis.ledkisbaseapp.ui.UiModule;

@Module(
        includes = {
                CoreModule.class,
                UiModule.class,
                DataModule.class,
                JobModule.class
        },
        injects = {
                LedkisBaseApplication.class,
        },
        complete = false,
        library = true
)
public class LedkisBaseAppModule {
    private final LedkisBaseApplication app;

    public LedkisBaseAppModule(LedkisBaseApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }
}
