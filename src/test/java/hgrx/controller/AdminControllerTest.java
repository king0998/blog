package hgrx.controller;

import org.junit.Test;

/**
 * Created by HGRX on 2017/5/29
 */
public class AdminControllerTest {
    @Test
    public void test01() {
        String str = "hello\\\\n nworld";
        System.out.println(str);
        String reStr = str.replaceAll("(?<!\\\\)\\\\n", "\n");
        System.out.println(reStr);
    }
}

