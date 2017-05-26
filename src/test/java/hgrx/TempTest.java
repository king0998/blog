package hgrx;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

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

}
