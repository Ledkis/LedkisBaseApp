package ledkis.ledkisbaseapp.data.api;

import android.content.Context;
import android.util.Log;


import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import bolts.Task;
import ledkis.ledkisbaseapp.LedkisBaseApplication;
import ledkis.ledkisbaseapp.core.AndroidBus;
import ledkis.ledkisbaseapp.core.Constants;
import ledkis.ledkisbaseapp.data.dao.DatabaseHelper;
import ledkis.ledkisbaseapp.data.model.CurrentUser;
import ledkis.ledkisbaseapp.events.LogInEvent;
import ledkis.ledkisbaseapp.events.UserUpdatedEvent;
import ledkis.ledkisbaseapp.util.Strings;
import ledkis.ledkisbaseapp.util.log.Ln;


/**
 * We don't add NetworkException cause we don't specialy need the error to be detected quickly (by a parseException)
 */
public class UserApi {

    public static final String TAG = "UserApi";

    @Inject AndroidBus bus;
    @Inject CurrentUser currentUser;

    Context context;

    public UserApi(Context context) {
        LedkisBaseApplication.get(context).inject(this);
        this.context = context;
    }

    public boolean isUserSignedIn() {
        return ParseUser.getCurrentUser() != null;
    }

    public void signIn(String username, String password) {

        try {
            currentUser.setUsername(username.toLowerCase());

            ParseUser.logIn(currentUser.getUsername(), password);

            bus.post(new LogInEvent(false));

            updateUser();
            subscribeToPersonnalChannel();

        } catch (ParseException e) {
        } catch (Exception e) {
        }

    }

    public Task<Void> signInAsync(final String username, final String password) {
        return Task.callInBackground(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                signIn(username, password);
                return null;
            }
        });
    }

    public void signUp(String username, String email, String password) {

        try {
            currentUser.setUsername(username.toLowerCase());
            currentUser.setEmail(email);

            ParseUser parseUser = new ParseUser();
            parseUser.setEmail(currentUser.getEmail());
            parseUser.setPassword(password);
            parseUser.setUsername(currentUser.getUsername());

            parseUser.signUp();
            bus.post(new LogInEvent(true));



            subscribeToPersonnalChannel();

            DatabaseHelper.initData(context);

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Task<Void> signUpAsync(final String username, final String email, final String password) {
        return Task.callInBackground(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                signUp(username, email, password);
                return null;
            }
        });
    }

    public void signOut() {

        if (isUserSignedIn()) {
            try {

                // Before loggin out
                updateUser();


                // Logging out
                unsubscribeToPersonnalChannel();
                ParseUser.logOut();

                // Reseting user
                currentUser.reset();
                DatabaseHelper.clearAllData(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public Task<Void> signOutAsync() {
        return Task.callInBackground(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                signOut();
                return null;
            }
        });
    }


    public void fetchUser(String requestType) {

        try {
            if (isUserSignedIn()) {
                ParseUser parseUser = ParseUser.getCurrentUser();

                parseUser.fetch();

                if (!Strings.isNullOrEmpty(parseUser.getObjectId())) {
                    currentUser.setUserId(parseUser.getObjectId());
                } else {
                    Ln.e(TAG, "userId is null");
                }

                if (!Strings.isNullOrEmpty(parseUser.getUsername())) {
                    currentUser.setUsername(parseUser.getUsername());
                } else {
                    Ln.e(TAG, "username is null");
                }

                if (!Strings.isNullOrEmpty(parseUser.getEmail())) {
                    currentUser.setEmail(parseUser.getEmail());
                } else {
                    Ln.e(TAG, "email is null");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser() {
        try {
            if (isUserSignedIn()) {

                ParseUser parseUser = ParseUser.getCurrentUser();
                // We do not update username & email cause it can't change
//                parseUser.put(USER_FULL_NAME, currentUser.getFullName());

                parseUser.save();

                bus.post(new UserUpdatedEvent());

            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Task<Void> updateUserAsync() {
        return Task.callInBackground(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                updateUser();
                return null;
            }
        });
    }

    public List<ParseUser> queryUserByUserIds(final List<String> userIds) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Constants.ApiKey.USER_CLASS);
        query.whereContainedIn(Constants.ApiKey.PARSE_OBJECT_ID, userIds);

        List<ParseUser> parseUsers = new ArrayList<>();

        // TODO max 100
        for (ParseObject parseUser : query.find()) {
            parseUsers.add((ParseUser) parseUser);
        }
        return parseUsers;
    }

    public void queryUserByUsernameAsync(final String username) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Constants.ApiKey.USER_CLASS);
        query.whereEqualTo(Constants.ApiKey.USERNAME, username.toLowerCase());

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseUser, ParseException e) {
                if (e == null) {
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    public void queryUserByEmailAsync(final String email) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Constants.ApiKey.USER_CLASS);
        query.whereEqualTo(Constants.ApiKey.USER_EMAIL, email);

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseUser, ParseException e) {
                if (e == null) {
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    private String getPersonnalChannel() {
        return Constants.Core.CHANNEL_FIRST_CHAR + currentUser.getUserId();
    }

    public void subscribeToPersonnalChannel() {

        if (!isUserSignedIn())
            return;

        final String channel = getPersonnalChannel();

        // Channel name must start with a letter
        ParsePush.subscribeInBackground(channel, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    public void subscribe(final String channel) {
        final String FUNCTION_TAG = "subscribe";

        if (!isUserSignedIn())
            return;

        ParsePush.subscribeInBackground(channel, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                } else {
                    e.printStackTrace();
                }
            }
        });

    }

    public void unsubscribe(final String channel) {
        final String FUNCTION_TAG = "unsubscribe";

        if (!isUserSignedIn())
            return;

        ParsePush.unsubscribeInBackground(channel, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    public void unsubscribeToPersonnalChannel() {
        if (!isUserSignedIn())
            return;
        final String channel = getPersonnalChannel();

        ParsePush.unsubscribeInBackground(channel, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                } else {
                    e.printStackTrace();
                }
            }
        });
    }


}