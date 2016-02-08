package ledkis.ledkisbaseapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static final String TAG = "Utils";

    public static final String NULL_ERROR_MESSAGE = "NULL";
    public static final String FULL_DATE_SIMPLE_FORMAT = "dd-MM-yyy HH:mm";

    /**
     * Returns whether the SDK is KitKat or later
     */
    public static boolean isJellyBeanOrLater() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean isKitKatOrLater() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
        return iterable == null ? Collections.<T>emptyList() : iterable;
    }

    /**
     * http://stackoverflow.com/questions/13758560/android-bitmap-to-byte-array-and-back-skimagedecoderfactory-returned-null
     */
    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static byte[] drawableToByteArray(Context context, int drawableResId) {
        Bitmap noPicturePicture = BitmapFactory.decodeResource(context.getResources(), drawableResId);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        noPicturePicture.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * http://stackoverflow.com/questions/13758560/android-bitmap-to-byte-array-and-back-skimagedecoderfactory-returned-null
     */
    public static Bitmap byteArrayToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public static String[] jsonArrayToArray(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list.toArray(new String[list.size()]);
    }

    public static int compare(int lhs, int rhs) {
        return lhs < rhs ? -1 : (lhs == rhs ? 0 : 1);
    }

    public static int getResourceId(Context context, String name, String type) {
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }

    public static boolean hasFlag(int flags, int flag) {
        return (flags & flag) == flag;
    }

    public static String getPathFromUrl(String url) {
        return url.split("\\?")[0];
    }

    public static int clampInt(int int1, int int2, int int3) {
        return Math.max(Math.min(int1, int2), int3);
    }

    public static String arrayListToString(ArrayList<String> array) {
        JSONArray a = new JSONArray();
        for (int i = 0; i < array.size(); i++) {
            a.put(array.get(i));
        }

        if (array.isEmpty()) {
            return null;
        } else {
            return a.toString();
        }
    }

    public static ArrayList<String> stringToArrayList(String value) {
        ArrayList<String> array = new ArrayList<>();
        if (value != null) {
            try {
                JSONArray a = new JSONArray(value);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    array.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return array;
    }

    public static byte[] getBytesFromFile(File file) {
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    public static float map(float value, float low1, float high1, float low2, float high2) {
        return low2 + (value - low1) * (high2 - low2) / (high1 - low1);
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getUUIDShortDisplayString(String uuid, int size) {
        if (Strings.isNullOrEmpty(uuid))
            return NULL_ERROR_MESSAGE;
        else
            return uuid.substring(0, size);
    }

    public static String getListUUIDShortDisplayString(List<String> uuids, int size) {
        String s = "";

        for (int i = 0; i < uuids.size(); i++) {
            if (i < uuids.size() - 1)
                s += getUUIDShortDisplayString(uuids.get(i), size) + "_";
            else
                s += getUUIDShortDisplayString(uuids.get(i), size);
        }

        return s;
    }

    public static LinkedHashMap<String, Boolean> sortLinkedHashMapOnKey(LinkedHashMap<String, Boolean> map) {
        // http://stackoverflow.com/questions/12184378/sorting-linkedhashmap
        List<Map.Entry<String, Boolean>> entries = new ArrayList<>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Boolean>>() {
            public int compare(Map.Entry<String, Boolean> a, Map.Entry<String, Boolean> b) {
                return a.getKey().compareTo(b.getKey());
            }
        });
        LinkedHashMap<String, Boolean> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Boolean> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    /**
     * http://stackoverflow.com/questions/4943629/how-to-delete-a-whole-folder-and-content
     */
    public static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.exists() && fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }

    public static List<String> listToStringList(List list) {
        List<String> stringList = new ArrayList<>();
        for (Object object : list) {
            stringList.add(object.toString());
        }
        return stringList;
    }

    public static String valueOrDefault(Object object, String defaultString) {
        if (object == null)
            return "";
        else
            return Strings.valueOrDefault(object.toString(), defaultString);
    }

    public static LinkedHashMap<String, String> getDisplayHashMap(LinkedHashMap<String, Object> map) {
        LinkedHashMap<String, String> displayableMap = new LinkedHashMap<>();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();

            if (value instanceof String) {
                displayableMap.put(entry.getKey(), Strings.valueOrDefault((String) entry.getValue(), NULL_ERROR_MESSAGE));
            } else if (value instanceof DateTime) {
                displayableMap.put(entry.getKey(), DateUtils.valueOrDefaultString((DateTime) entry.getValue(), FULL_DATE_SIMPLE_FORMAT, NULL_ERROR_MESSAGE));
            } else if (value instanceof JSONArray) {
                displayableMap.put(entry.getKey(), Strings.concatStrings(JSONUtils.jsonArrayToStringList((JSONArray) entry.getValue()), " "));
            } else if (value instanceof List) {
                displayableMap.put(entry.getKey(), Strings.concatStrings((listToStringList((List) entry.getValue())), " "));
            } else {
                displayableMap.put(entry.getKey(), valueOrDefault(entry.getValue(), NULL_ERROR_MESSAGE));
            }
        }
        return displayableMap;
    }

    public static int getFileSize(File file) {
        return Integer.parseInt(String.valueOf(file.length() / 1024));
    }

    public static String toStringOrDefault(Object object, String defaultString) {
        if (object == null)
            return defaultString;
        return Strings.valueOrDefault(object.toString(), defaultString);
    }

    public static SpannableStringBuilder getColoredFilteredString(String filter, String target, int filterColor) {

        final SpannableStringBuilder sb = new SpannableStringBuilder(target);

        if (!Strings.isNullOrEmpty(filter) && target.toLowerCase().contains(filter)) {

            // http://stackoverflow.com/questions/2615749/java-method-to-get-position-of-a-match-in-a-string
            int from = target.toLowerCase().indexOf(filter);
            int to = from + filter.length();

            // http://stackoverflow.com/questions/4897349/android-coloring-part-of-a-string-using-textview-settext
            final ForegroundColorSpan fcs = new ForegroundColorSpan(filterColor);
            final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);

            sb.setSpan(fcs, from, to, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            sb.setSpan(bss, from, to, Spannable.SPAN_INCLUSIVE_INCLUSIVE);


        }

        return sb;
    }

    public static List<Integer> getDefaultValuedList(int size, int value) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(value);
        }

        return list;
    }

    public static List<Boolean> getDefaultValuedList(int size, boolean value) {
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(value);
        }

        return list;
    }

    public static float clamp(float value, float min, float max) {
        return Math.min(max, Math.max(value, min));
    }


}
