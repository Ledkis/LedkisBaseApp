package ledkis.ledkisbaseapp.util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class JSONUtils {

    public static String[] jsonArrayToStringArray(JSONArray jsonArray) {
        String[] stringArray = null;
        try {
            if (jsonArray != null) {
                int len = jsonArray.length();
                stringArray = new String[len];
                for (int i = 0; i < len; i++) {
                    stringArray[i] = jsonArray.get(i).toString();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stringArray;
    }

    public static List<String> jsonArrayToStringList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        try {
            if (jsonArray != null) {
                int len = jsonArray.length();
                for (int i = 0; i < len; i++) {
                    // TODO moche : toString
                    list.add((String) jsonArray.get(i).toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Boolean> jsonArrayToBooleanList(JSONArray jsonArray) {
        List<Boolean> list = new ArrayList<>();
        try {
            if (jsonArray != null) {
                int len = jsonArray.length();
                for (int i = 0; i < len; i++) {
                    list.add((Boolean) jsonArray.get(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Integer> jsonArrayToIntegerList(JSONArray jsonArray) {
        List<Integer> list = new ArrayList<>();
        try {
            if (jsonArray != null) {
                int len = jsonArray.length();
                for (int i = 0; i < len; i++) {
                    list.add((Integer) jsonArray.get(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }


}
