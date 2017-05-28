package hgrx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HGRX on 2017/5/28
 */
public class ElFunUtil {

    /**
     * 将Long格式的Unix时间戳转换为 yyyy/mm/dd 格式的字符串
     */

    public static String date(Long timestamp) {
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date(timestamp));
    }
}
