package hgrx.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatisUtils {
    private static SqlSessionFactory factory;

    public static SqlSession getSqlSession() {
        try {
            if (factory == null) {
                synchronized (MyBatisUtils.class) {
                    if (factory == null) {
                        InputStream inputStream = Resources.getResourceAsStream("config/mybatis/mybatis-config.xml");
                        factory = new SqlSessionFactoryBuilder().build(inputStream);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory.openSession();
    }
}
