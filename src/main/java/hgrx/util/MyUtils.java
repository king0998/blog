package hgrx.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by HGRX on 2017/5/15
 */
public class MyUtils {

    public static Long SYSTEM_USERID = 4L;

    @SuppressWarnings("unchecked")
    public static List<String> transformTagsToList(String str) {
        return new Gson().fromJson(str, ArrayList.class);
    }

    /**
     *   ["java","linux","spring"]
     */
    public static String transformListToTags(List<String> list) {
        return new Gson().toJson(list);
    }


    public static String getJSONString(int code) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        return json.toJSONString();
    }

    public static String getJSONString(int code, String msg) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json.toJSONString();
    }

    public static String getJSONString(int code, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            json.put(entry.getKey(), entry.getValue());
        }
        return json.toJSONString();
    }

}
