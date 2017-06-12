package hgrx.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by HGRX on 2017/5/15
 */
public class MyUtils {
    private MyUtils() {
    }

    public static final int COMMENT_ENTITY_TYPE_ARTICLE = 1;
    public static final int COMMENT_ENTITY_TYPE_ABOUT = 2;
    public static final int COMMENT_ENTITY_TYPE_COMMENT = 3;

    public static Long SYSTEM_USERID = 4L;

    @SuppressWarnings("unchecked")
    public static List<String> transformTagsToList(String str) {
        List<String> list = new Gson().fromJson(str, ArrayList.class);
        list.removeIf(Objects::isNull);
        return list;
    }

    /**
     * ["java","linux","spring"]
     */
    public static String transformListToTags(List<String> list) {
        return new Gson().toJson(list);
    }

    public static String getJSONString(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", msg);
        return jsonObject.toJSONString();
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

    public static String getJSONString(Boolean aBoolean) {
        JSONObject json = new JSONObject();
        json.put("code", aBoolean ? 200 : 400);
        json.put("msg", aBoolean ? "操作成功!" : "操作失败!");
        return json.toJSONString();
    }
}
