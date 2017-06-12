package hgrx.util;

import org.junit.Test;

/**
 * Created by HGRX on 2017/6/11
 */
public class MyUtilsTest {
    @Test
    public void getJSONString() throws Exception {
        System.out.println(MyUtils.getJSONString(true));
        System.out.println(MyUtils.getJSONString(false));
    }

    @Test
    public void test01() {
        System.out.println(MyUtils.transformTagsToList("[,,java,,.,spring,,]"));
    }

}