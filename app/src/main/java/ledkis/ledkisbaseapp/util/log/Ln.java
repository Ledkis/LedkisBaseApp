package ledkis.ledkisbaseapp.util.log;

import android.content.ContextWrapper;

import timber.log.Timber;


public class Ln {

    public static final String SCREEN_STATE_FLAG = "ScreenState";

    private static final boolean STATE_LOGGING = true;

    public static void init(ContextWrapper context) {
//        if (BuildConfig.DEBUG || BuildConfig.BETA) {
//            Timber.plant(new Timber.DebugTree());
//        } else {
//            Timber.plant(new CrashReportingTree(context));
//        }

        Timber.plant(new Timber.DebugTree());
    }

    /**
     * Log a verbose message with optional format args.
     */
    public static void v(String tag, String message, Object... args) {
        tag(tag);
        Timber.v(message, args);
    }

    /**
     * Log a verbose exception and a message with optional format args.
     */
    public static void v(String tag, Throwable t, String message, Object... args) {
        tag(tag);
        Timber.v(t, message, args);
    }

    /**
     * Log a debug message with optional format args.
     */
    public static void d(String tag, String message, Object... args) {
        tag(tag);
        Timber.d(message, args);
    }

    /**
     * Log a debug exception and a message with optional format args.
     */
    public static void d(String tag, Throwable t, String message, Object... args) {
        tag(tag);
        Timber.d(t, message, args);
    }

    /**
     * Log an info message with optional format args.
     */
    public static void i(String tag, String message, Object... args) {
        tag(tag);
        Timber.i(message, args);
    }

    /**
     * Log an info exception and a message with optional format args.
     */
    public static void i(String tag, Throwable t, String message, Object... args) {
        tag(tag);
        Timber.i(t, message, args);
    }

    /**
     * Log a warning message with optional format args.
     */
    public static void w(String tag, String message, Object... args) {
        tag(tag);
        Timber.w(message, args);
    }

    /**
     * Log a warning exception and a message with optional format args.
     */
    public static void w(String tag, Throwable t, String message, Object... args) {
        tag(tag);
        Timber.w(t, message, args);
    }

    /**
     * Log an error message with optional format args.
     */
    public static void e(String tag, String message, Object... args) {
        tag(tag);
        Timber.e(message, args);
    }

    /**
     * Log an error exception and a message with optional format args.
     */
    public static void e(String tag, Throwable t, String message, Object... args) {
        tag(tag);
        Timber.e(t, message, args);
    }

    /**
     * Set a one-time tag for use on the next logging call.
     */
    public static void tag(String tag) {
        Timber.tag(tag);
    }

    public static void xv(String message, Object... args) {
        String tag = "XXX";
        tag(tag);
        Timber.v(null, message, args);
    }


    public static void xd(String message, Object... args) {
        String tag = "XXX";
        tag(tag);
        Timber.d(null, message, args);
    }


    public static void screenState(String screenName, String state, boolean log) {
        if (log && STATE_LOGGING && !"".equals(screenName))
            i(SCREEN_STATE_FLAG, screenName + " " + state);
    }

}

