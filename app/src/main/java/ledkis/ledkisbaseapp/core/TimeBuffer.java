package ledkis.ledkisbaseapp.core;

import android.os.Handler;


public class TimeBuffer {

    public static final String TAG = "TimeBuffer";

    public static final boolean VERBOSE = true;


    private String id;
    private Buffer timeBuffer;

    public TimeBuffer() {
        id = System.currentTimeMillis() % 1000 + "";
    }

    public TimeBuffer(String id) {
        this.id = id;
    }

    public synchronized void start(final long timeSize, final Runnable callback) {

        if (null == timeBuffer) { // !isRunning
            timeBuffer = new Buffer(timeSize, new Runnable() {
                @Override
                public void run() {
                    timeBuffer = null;
                    if (VERBOSE) {
                    }

                    callback.run();
                }
            });
            timeBuffer.start();
        } else { // has already ben started
            timeBuffer.start();
        }
    }

    @Override
    public String toString() {
        return TAG + "{" + id + "}";
    }

    private class Buffer {

        private Runnable callback;
        private long timeSize;
        private Handler handler;

        public Buffer(long timeSize, Runnable callback) {
            this.timeSize = timeSize;
            this.callback = callback;
            handler = new Handler();

        }

        public void start() {
            handler.removeCallbacks(callback);
            handler.postDelayed(callback, timeSize);
        }
    }

}