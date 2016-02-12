package ledkis.ledkisbaseapp.ui;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ledkis.ledkisbaseapp.ui.activity.MainActivity;
import ledkis.ledkisbaseapp.ui.fragments.CenterFragment;
import ledkis.ledkisbaseapp.ui.util.UiPreference;

@Module(
        injects = {
                MainActivity.class,
//                CenterFragment.class,
        },
        complete = false,
        library = true
)
public class UiModule {

    @Provides
    @Singleton
    UiPreference provideUiPreference(final Context context) {
        return new UiPreference(context);
    }

}
