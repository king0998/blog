package hgrx.util;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by HGRX on 2017/3/24
 */
public class RegexUtils {
    private RegexUtils() {
    }

    private static final String fileNameRegex = "\\d{1,20}-[\\u4e00-\\u9fa5]{2,5}-[\\u4E00-\\u9FA5\\w-_]+{1,50}";

    // 非数字开头的 3到16位字母数字
    private static final String usernameRegex = "^[a-zA-Z0-9]{3,16}$";

    //
    private static final String emailRegex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    // 非数字开头的 3到16位字母数字
    private static final String passwordRegex = "^[a-zA-Z0-9]{3,16}$";
    private static final String CDKey = "HGRX";

    public static boolean isUsername(String username) {
        return username.matches(usernameRegex);
    }

    public static boolean isEmail(String email) {
        return email.matches(emailRegex);
    }

    public static boolean isPassword(String password) {
        return password.matches(passwordRegex);
    }

    public static boolean isCDKey(String code) {
        return CDKey.equals(code);
    }

    public static String getFileNameRegex() {
        return fileNameRegex;
    }

    public static boolean checkTagsStr(String tags) {
        boolean f = true;
        try {
            new Gson().fromJson(tags, ArrayList.class);
        } catch (Exception e) {
            f = false;
        }
        return hasIllegalChar(tags) && f;
    }

    public static boolean hasIllegalChar(String nickname) {
        return !nickname.contains("<") &&
                !nickname.contains(".") &&
                !nickname.contains(">") &&
                !nickname.contains("\"") &&
                !nickname.contains("'") &&
                !nickname.contains("&") &&
                !nickname.contains("|");
    }
}
