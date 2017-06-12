package hgrx.util;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HGRX on 2017/5/28
 */
public class ElFunUtil {

    private ElFunUtil() {
    }

    /**
     * 将Long格式的Unix时间戳转换为 yyyy/mm/dd 格式的字符串
     */

    public static String date(Long timestamp) {
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date(timestamp));
    }

    public static String datehhmm(Long timestamp) {
        return new SimpleDateFormat("yyyy/MM/dd-HH:mm").format(new Date(timestamp));
    }


    public static String md(String md) {

        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(md);
        return renderer.render(document);
    }
}
