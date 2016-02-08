package ledkis.ledkisbaseapp.data;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ledkis.ledkisbaseapp.data.api.ApiModule;
import ledkis.ledkisbaseapp.data.dao.DaoModule;
import ledkis.ledkisbaseapp.data.model.CurrentUser;

@Module(
        includes = {
                ApiModule.class,
                DaoModule.class
        },
        complete = false,
        library = true
)

public class DataModule {
    @Provides
    @Singleton
    CurrentUser provideCurrentUser(Context context) {
        return new CurrentUser(context);
    }


}


