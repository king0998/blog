package hgrx.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListenerImpl implements Runnable, ServletContextListener {

    @Override
    public void run() {

    }

    // 应用启动时，该方法会被调用
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("应用启动......");
    }

    // 应用关闭时，该方法会被调用
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("应用关闭......");
    }
}