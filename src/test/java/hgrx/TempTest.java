package hgrx;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import hgrx.util.VerifyCodeUtils;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Test
    public void testBase64() throws IOException {
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        int w = 146, h = 33;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, byteArrayOutputStream, verifyCode);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String base64Code = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
        System.out.println(base64Code);
    }

    @Test
    public void test023() {
        byte[] bytes = {-1, -40, 33};
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] < 0) {// 调整异常数据
                bytes[i] += 256;
            }
        }

        bytes[0] += 256;
        System.out.println(bytes[0]);

        byte t = -1;
        t += 256;
        System.out.println(t + 256);

        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
    }

    @Test
    public void test011() {
        System.out.println(new Gson().toJson(new HashMap<>()));
    }
}

