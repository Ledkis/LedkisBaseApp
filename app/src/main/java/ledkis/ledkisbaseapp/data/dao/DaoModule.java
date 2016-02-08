package ledkis.ledkisbaseapp.data.dao;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                DaoDataExampleRepo.class,
        },
        complete = false,
        library = true
)
public class DaoModule {

    @Provides
    @Singleton
    DatabaseHelper provideDatabaseHelper(final Context context) {
        return new DatabaseHelper(context);
    }

}
