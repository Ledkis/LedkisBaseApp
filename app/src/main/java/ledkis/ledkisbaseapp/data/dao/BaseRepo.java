package ledkis.ledkisbaseapp.data.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import ledkis.ledkisbaseapp.LedkisBaseApplication;
import ledkis.ledkisbaseapp.util.log.Ln;

public class BaseRepo<T, F> {
    @Inject protected DatabaseHelper databaseHelper;

    protected static String TAG = "BaseRepo";

    final Class<T> typeParameterClass;
    protected Context context;
    private Dao<T, F> daoInstance = null;

    public BaseRepo(Class<T> typeParameterClass, Context context) {
        this.typeParameterClass = typeParameterClass;
        this.context = context;
        LedkisBaseApplication.get(context).inject(this);
    }

    public Dao getDao(Class typeParameterClass) throws SQLException {
        return databaseHelper.getDao(typeParameterClass);
    }

    public Dao<T, F> getDao() throws SQLException {
        if (daoInstance == null) {
            daoInstance = databaseHelper.getDao(typeParameterClass);
            return daoInstance;
        }
        return daoInstance;
    }

    public T getById(F id) {
        try {
            Dao<T, F> dao = getDao();
            return dao.queryForId(id);
        } catch (SQLException e) {
            Ln.e(TAG, "getById", e);
            e.printStackTrace();
        }
        return null;
    }

    public boolean exists(F id) {
        try {
            Dao<T, F> dao = getDao();
            return dao.idExists(id);
        } catch (SQLException e) {
            Ln.e(TAG, "exists", e);
            e.printStackTrace();
        }
        return false;
    }

    public int create(T object) {
        try {
            Dao<T, F> dao = getDao();
            return dao.create(object);
        } catch (SQLException e) {
            Ln.e(TAG, "create", e);
            e.printStackTrace();
        }
        return 0;
    }

    public int create(List<T> objects) {
        int result = 0;
        for (T object : objects) {
            try {
                Dao<T, F> dao = getDao();
                result += dao.create(object);
            } catch (SQLException e) {
                Ln.e(TAG, "create", e);
                e.printStackTrace();
            }
        }
        return result;
    }

    public void createAsync(T... objects) {
        AsyncTask<T, Void, Void> task = new AsyncTask<T, Void, Void>() {
            @Override
            protected Void doInBackground(T... params) {
                for (T param : params) {
                    try {
                        if (param != null) {
                            create(param);
                        }
                    } catch (Exception e) {
                        Ln.e(TAG, "createAsync", e);
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
        task.execute(objects);
    }

    public int update(T object) {
        try {
            Dao<T, F> dao = getDao();
            return dao.update(object);
        } catch (SQLException e) {
            Ln.e(TAG, "update", e);
            e.printStackTrace();
        }
        return 0;
    }

    public int update(List<T> objects) {
        int result = 0;
        for (T object : objects) {
            try {
                Dao<T, F> dao = getDao();
                result += dao.update(object);
            } catch (SQLException e) {
                Ln.e(TAG, "update", e);
                e.printStackTrace();
            }
        }
        return result;
    }

    public void updateAsync(T... objects) {
        AsyncTask<T, Void, Void> task = new AsyncTask<T, Void, Void>() {
            @Override
            protected Void doInBackground(T... params) {
                for (T param : params) {
                    try {
                        if (param != null) {
                            update(param);
                        }
                    } catch (Exception e) {
                        Ln.e(TAG, "updateAsync", e);
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
        task.execute(objects);
    }

    public void refresh(T object) {
        try {
            Dao<T, F> dao = getDao();
            dao.refresh(object);
        } catch (SQLException e) {
            Ln.e(TAG, "refresh", e);
            e.printStackTrace();
        }
    }

    public int delete(T object) {
        try {
            Dao<T, F> dao = getDao();
            return dao.delete(object);
        } catch (SQLException e) {
            Ln.e(TAG, "delete", e);
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(List<T> objects) {
        try {
            Dao<T, F> dao = getDao();
            return dao.delete(objects);
        } catch (SQLException e) {
            Ln.e(TAG, "delete", e);
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteAll() {
        try {
            Dao<T, F> dao = getDao();
            return dao.delete(getAll());
        } catch (SQLException e) {
            Ln.e(TAG, "deleteAll", e);
            e.printStackTrace();
        }
        return 0;
    }

    public int createOrUpdate(T object) {
        try {
            Dao<T, F> dao = getDao();
            return dao.createOrUpdate(object).getNumLinesChanged();
        } catch (SQLException e) {
            Ln.e(TAG, "createOrUpdate", e);
            e.printStackTrace();
        }
        return 0;
    }

    public void createOrUpdate(final List<T> objects) {
        try {
            final Dao<T, F> dao = getDao();
            dao.callBatchTasks(
                    new Callable<Void>() {
                        public Void call() throws SQLException {
                            // insert a number of accounts at once
                            for (T object : objects) {
                                dao.createOrUpdate(object);
                            }
                            return null;
                        }
                    });
        } catch (SQLException e) {
            Ln.e(TAG, "createOrUpdate", e);
            e.printStackTrace();
        } catch (Exception e) {
            Ln.e(TAG, "createOrUpdate", e);
            e.printStackTrace();
        }
    }

    public List<T> getAll() {
        try {
            Dao<T, F> dao = getDao();
            return dao.queryForAll();
        } catch (SQLException e) {
            Ln.e(TAG, "getAll", e);
            e.printStackTrace();
        }
        return null;
    }

    public T getFirst() {
        T result = null;
        try {
            Dao<T, F> dao = getDao();
            QueryBuilder<T, F> builder = dao.queryBuilder();
            builder.limit(1l);
            result = dao.queryForFirst(builder.prepare());
        } catch (SQLException e) {
            Ln.e(TAG, "getFirst", e);
            e.printStackTrace();
        }
        return result;
    }

    public int count() {
        int result = -1;
        try {
            Dao<T, F> dao = getDao();
            result = (int) dao.countOf();
        } catch (SQLException e) {
            Ln.e(TAG, "count", e);
            e.printStackTrace();
        }
        return result;
    }

    public boolean isEmpty() {
        return count() < 1;
    }

}
