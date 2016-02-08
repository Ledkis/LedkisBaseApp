package ledkis.ledkisbaseapp.ui.util;

import android.content.Context;
import android.content.SharedPreferences;

public class UiPreference {
    protected final static String PREF_FILE_NAME = "uipref";

    SharedPreferences preferences;

    public UiPreference(Context context) {
        preferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

}
