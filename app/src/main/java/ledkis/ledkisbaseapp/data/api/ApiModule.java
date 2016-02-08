package ledkis.ledkisbaseapp.data.api;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                UserApi.class,
        },
        complete = false,
        library = true
)
public class ApiModule {

    @Singleton
    @Provides
    UserApi provideUserApi(final Context context) {
        return new UserApi(context);
    }

}
