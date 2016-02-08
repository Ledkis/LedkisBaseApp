package ledkis.ledkisbaseapp.data.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;

import ledkis.ledkisbaseapp.data.model.DaoDataExample;

public class DaoDataExampleRepo extends BaseRepo<DaoDataExample, Integer> {

    public DaoDataExampleRepo(Context context) {
        super(DaoDataExample.class, context);
        TAG = "DaoDataExample";
    }
}
