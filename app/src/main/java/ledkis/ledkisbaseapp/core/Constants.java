package ledkis.ledkisbaseapp.core;

import java.util.regex.Pattern;

public class Constants {

    public static class Core {

        public static final String CHANNEL_FIRST_CHAR = "u_";

    }

    public static class ApiKey {
        // Parse Object

        public static final String PARSE_OBJECT_ID = "objectId";

        // Back end
        public static final String USER_CLASS = "_User";
        public static final String USER_POINTER_NAME = "user";
        public static final String USER_ID = "userId";
        public static final String USERNAME = "username";
        public static final String USER_EMAIL = "email";

    }

    public static class Ui {

    }

    public static final class Job {

        public static final int MIN_CONSUMER_COUNT = 1; //always keep at least one consumer alive
        public static final int MAX_CONSUMER_COUNT = 3;//up to 3 consumers at a time
        public static final int LOAD_FACTOR = 3;//3 jobs per consumer
        public static final int CONSUMER_KEEP_ALIVE = 120;//wait 2 minute

        public static final int PRIORITY_FETCH = 2;
        public static final int PRIORITY_UPDATE = 3;
    }

}
