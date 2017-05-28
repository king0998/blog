package hgrx;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by HGRX on 2017/5/11
 */
public class TempTest {
    @Test
    public void test01() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void test02() {
        List<String> list = new ArrayList<>(Arrays.asList("java", "linux", "spring"));
        System.out.println(new Gson().toJson(list));
    }

    @Test
    public void test03() {
        String str = "java,list,linux";
        System.out.println(new Gson().fromJson(str, ArrayList.class));
    }

    @Test
    public void test04() {
        ArrayList<String> a = Lists.newArrayList("1", "2", "3", "4");
        ArrayList<String> b = Lists.newArrayList("3", "4", "5", "6");
        System.out.println(CollectionUtils.retainAll(a, b));
    }

    @Test
    public void testTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        System.out.println(sdf.format(new Date(1495784252044L)));
    }

    @Test
    public void testJavaMD() {
        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse("### hello");
        String html = renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
        System.out.println(html);
    }
}

