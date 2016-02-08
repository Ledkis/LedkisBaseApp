package ledkis.ledkisbaseapp.data.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import ledkis.ledkisbaseapp.core.Constants;
import ledkis.ledkisbaseapp.util.Strings;
import ledkis.ledkisbaseapp.util.log.Ln;

public class CurrentUser {

    public static final String TAG = "CurrentUser";

    public final static String PREF_FILE_NAME = "user";

    private SharedPreferences prefs;

    /**
     * Used only for logging
     */
    private String _username = "";

    public CurrentUser(Context context) {
        prefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        _username = getUsername();
    }

    public void reset() {
        setUserId("");
        // We don't need to reset the username since it is set in SignIn & SingUp. So we can use it in suggestion in signIn
//        setUsername("");
    }

    public String getUserId() {
        String userId = prefs.getString(Constants.ApiKey.USER_ID, "");
        return userId;
    }

    public void setUserId(String userId) {
        setLogging("setUserId", userId);
        prefs.edit().putString(Constants.ApiKey.USER_ID, userId).commit();
    }

    public String getUsername() {
        return prefs.getString(Constants.ApiKey.USERNAME, "");
    }

    public void setUsername(String username) {
        setLogging("setUsername", username);
        _username = username;
        prefs.edit().putString(Constants.ApiKey.USERNAME, username).commit();
    }

    public String getEmail() {
        return prefs.getString(Constants.ApiKey.USER_EMAIL, "");
    }

    public void setEmail(String email) {
        setLogging("setEmail", email);
        prefs.edit().putString(Constants.ApiKey.USER_EMAIL, email).commit();
    }

    private void setLogging(String function, Object value) {
        Ln.v(TAG, function + ": " + Strings.valueOrDefault(value.toString(), "NULL") + " [" + _username + "]");
    }

}
