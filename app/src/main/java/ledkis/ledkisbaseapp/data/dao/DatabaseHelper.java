package ledkis.ledkisbaseapp.data.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import ledkis.ledkisbaseapp.data.model.DaoDataExample;
import ledkis.ledkisbaseapp.util.log.Ln;

/**
 * Database helper class used to manage the creation and upgrading of your
 * database. This class also usually provides the DAOs used by the other
 * classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    protected static final String TAG = "DatabaseHelper";

    // name of the database file for your application -- change to something
    // appropriate for your app
    public static final String DATABASE_NAME = "app.db"; // TODO change
    // any time you make changes to your database, you may have to increase the
    // database version
    private static final int DATABASE_VERSION = 1;

    Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /**
     * This is called when the database is first created. Usually you should
     * call createTable statements here to create the tables that will store
     * your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Ln.i(TAG, "database creation");
            TableUtils.createTable(connectionSource, DaoDataExample.class);

            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(context.getAssets().open("v1.sql")), 1024 * 4);
                String line = null;
                db.beginTransaction();
                while ((line = br.readLine()) != null) {
                    db.execSQL(line);
                }
                db.setTransactionSuccessful();
            } catch (IOException e) {
                Ln.e(TAG, "read database init file error", e);
            } finally {
                db.endTransaction();
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        Ln.e(TAG, "buffer reader close error", e);
                    }
                }
            }
        } catch (SQLException e) {
            Ln.e(TAG, "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    public static void initData(Context context) {

    }

    public static void initContacts(Context context) {

    }

    public static void clearAllData(Context context) {
        final String FUNCTION_TAG = "computeShouldIAnswer";

    }

    /**
     * This is called when the application is upgraded and it has a higher
     * version number. This allows you to adjust the various data to match the
     * new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
//        Ln.i(TAG, "onUpgrade, oldVersion=[" + oldVersion + "], newVersion=[" + newVersion + "]");
//        try {
//            //first public release
//            if (oldVersion < 3) {
//                Ln.i(TAG, "database upgarde");
//                TableUtils.dropTable(connectionSource, Ritual.class, true);
//                TableUtils.dropTable(connectionSource, Reminder.class, true);
//                TableUtils.dropTable(connectionSource, Habit.class, true);
//                TableUtils.dropTable(connectionSource, UserHabit.class, true);
//                TableUtils.dropTable(connectionSource, UserAction.class, true);
//                TableUtils.dropTable(connectionSource, Stat.class, true);
//                TableUtils.dropTable(connectionSource, Tip.class, true);
//                TableUtils.dropTable(connectionSource, Card.class, true);
//
//                // after we drop the old databases, we create the new ones
//                onCreate(db, connectionSource);
//
//                SharedPreferencesHelper.reset(context);
//                return;
//            }
//
//            // Add migration for version if required
//            while (++oldVersion <= newVersion) {
//                switch (oldVersion) {
//                    case 4: {
//                        UpgradeHelper.addUpgrade(4);
//                        break;
//                    }
//                    case 5: {
//                        UpgradeHelper.addUpgrade(5);
//                        break;
//                    }
//                    case 6: {
//                        TableUtils.createTable(connectionSource, Training.class);
//                        TableUtils.createTable(connectionSource, TrainingStep.class);
//                        break;
//                    }
//                    case 7: {
//                        UpgradeHelper.addUpgrade(7);
//                        break;
//                    }
//                    case 8: {
//                        UpgradeHelper.addUpgrade(8);
//                        break;
//                    }
//                    case 9: {
//                        TableUtils.createTable(connectionSource, Report.class);
//                        break;
//                    }
//                    case 10: {
//                        UpgradeHelper.addUpgrade(10);
//                        break;
//                    }
//                    case 11: {
//                        TableUtils.createTable(connectionSource, Skill.class);
//                        TableUtils.createTable(connectionSource, SkillLevel.class);
//                        TableUtils.createTable(connectionSource, SkillTrack.class);
//                        TableUtils.createTable(connectionSource, SkillGoal.class);
//                        UpgradeHelper.addUpgrade(11);
//                        break;
//                    }
//                    case 12: {
//                        UpgradeHelper.addUpgrade(12);
//                        break;
//                    }
//                    case 13: {
//                        UpgradeHelper.addUpgrade(13);
//                        break;
//                    }
//                    case 14: {
//                        UpgradeHelper.addUpgrade(14);
//                        break;
//                    }
//                }
//            }
//            // Get all the available updates
//            final List<String> availableUpdates = UpgradeHelper.availableUpdates(this.context.getResources());
//            Ln.d(TAG, "Found a total of %s update statements", availableUpdates.size());
//
//            for (final String statement : availableUpdates) {
//                db.beginTransaction();
//                try {
//                    Ln.d("Executing statement: %s", statement);
//                    db.execSQL(statement);
//                    db.setTransactionSuccessful();
//                } catch (Exception e) {
//                    Ln.e(TAG, e, "Ssl statement failed :" + statement);
//                } finally {
//                    db.endTransaction();
//                }
//            }
//
//        } catch (SQLException e) {
//            Ln.e(TAG, e, "Can't upgrade databases");
//            throw new RuntimeException(e);
//        }
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
    }
}
