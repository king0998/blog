package hgrx.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HGRX on 2017/6/12
 */
public class StartListener implements Runnable, ServletContextListener {

    private ServletContext servletContext;
    private List<String> data;

    public StartListener() {
    }

    public StartListener(ServletContext servletContext, List<String> data) {
        this.servletContext = servletContext;
        this.data = data;
        System.out.println("StarListener启动完成");
    }

    @Override
    public void run() {
        while (true) {
            servletContext.setAttribute("interesting", data.get((int) (Math.random() * 1000) % data.size()));
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("interesting");
        InputStreamReader read = new InputStreamReader(is, Charset.forName("utf-8"));
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt;
        List<String> data = new ArrayList<>();
        try {
            while ((lineTxt = bufferedReader.readLine()) != null) {
                lineTxt = lineTxt.trim();
                data.add(lineTxt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(data);
        new Thread(new StartListener(servletContext, data)).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
