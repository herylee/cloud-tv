package joshua.cloudtv.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by JoshuaShaw on 2016/12/5.
 */
public class JsonKit {

    private static Gson GSON = new Gson();

    public static String toJson(Object o) {
        return GSON.toJson(o);
    }

    public static void printJson(Object o) {
        System.out.println(toJson(o));
    }

    public static void test(Object o) {
        GsonBuilder gb = new GsonBuilder();
        ExclusionStrategy es = new ExclusionStrategy() {
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                return false;
            }

            public boolean shouldSkipClass(Class<?> aClass) {
                return false;
            }
        };
        gb.setExclusionStrategies(es);
    }
}
