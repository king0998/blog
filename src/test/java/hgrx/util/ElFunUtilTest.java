package hgrx.util;

import org.junit.Test;

import java.util.Date;

/**
 * Created by HGRX on 2017/5/28
 */
public class ElFunUtilTest {
    @Test
    public void date() throws Exception {
        System.out.println(ElFunUtil.date(System.currentTimeMillis()));
    }

    @Test
    public void equals() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testDate() {
        System.out.println(ElFunUtil.date(((1495975591000L))));
        System.out.println(new Date(1495975591000L));
    }
}