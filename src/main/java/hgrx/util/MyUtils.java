package hgrx.util;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HGRX on 2017/5/15
 */
public class MyUtils {

    @SuppressWarnings("unchecked")
    public static List<String> transformTagsToList(String str) {
        return new Gson().fromJson(str, ArrayList.class);
    }

    /**
     * ["java","linux","spring"]
     */
    public static String transformListToTags(List<String> list) {
        return new Gson().toJson(list);
    }

}
