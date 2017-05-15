package hgrx;

import com.google.gson.Gson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
  
}
