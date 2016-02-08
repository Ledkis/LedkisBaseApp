package ledkis.ledkisbaseapp.data.model;

import com.j256.ormlite.field.DatabaseField;

import org.joda.time.DateTime;

public class DaoDataExample {

    @DatabaseField(generatedId = true, uniqueIndex = true)
    private int id;
    @DatabaseField
    private String type;

    public DaoDataExample() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "DaoDataExample[" + id + "]";
    }

}