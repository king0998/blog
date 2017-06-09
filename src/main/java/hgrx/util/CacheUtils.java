package hgrx.util;

import hgrx.bean.Tag;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by HGRX on 2017/6/5
 */
public enum CacheUtils {
    MyCache;


    static Log log = LogFactory.getLog(CacheUtils.class);
    //储存需要两个变量来标识是否更新缓存的,那两个标识
    private ConcurrentHashMap<String, Integer> AB = new ConcurrentHashMap<>();
    //缓存数据放这
    private ConcurrentHashMap<String, Object> data = new ConcurrentHashMap<>();
    //单个读写锁
    private ConcurrentHashMap<String, ReentrantReadWriteLock> locks = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<Long, Integer> pageViews;

    static {    //初始化阅读量数据并定时写入本地文件
        try {
            log.info("开始数据初始化");
            pageViews = CacheUtils.loadLocalPageViewsData();
        } catch (IOException e) {
            log.error("初始化阅读量数据文件失败,尝试new一个新的");
            log.error(e.getMessage());
            pageViews = new ConcurrentHashMap<>();
        } finally {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(60 * 1000);
                    } catch (InterruptedException e) {
                        //无关紧要
                        log.error(e.getMessage());
                    }
                    savePageViewsData();
                }
            }).start();
        }
    }

    private static void savePageViewsData() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("D:\\code\\myblog\\src\\main\\resources\\pageViewsData")));
            oos.writeObject(pageViews);
            oos.close();
            log.info("持久化成功");
        } catch (IOException e) {
            System.exit(1);
            // 在本次运行中是没有问题的,但是重启之后数据就没了,所以还是直接宕掉吧
        }
    }

    @SuppressWarnings("unchecked")
    private static ConcurrentHashMap<Long, Integer> loadLocalPageViewsData() throws IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("D:\\code\\myblog\\src\\main\\resources\\pageViewsData")));
        Map<Long, Integer> map = null;
        try {
            map = (Map<Long, Integer>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);//正常情况不可能,但还是直接宕掉吧
        }

        log.debug("阅读初始化数据如下! " + map);
        return new ConcurrentHashMap<>(map);
    }

    public int getAbValue(String key) {
        Integer t = AB.get(key);
        if (t == null) {
            //第一次插入A值与B值必须保证A大于B,以便载入缓存
            if (key.endsWith("-A")) {
                AB.put(key, 1);
            } else {
                AB.put(key, 0);
            }
            return AB.get(key);
        }
        return t;
    }

    public boolean isChanged(String prefix) {
        return (MyCache.getAbValue(prefix + "-A") != MyCache.getAbValue(prefix + "-B"));
    }

    public Object getCache(String key) {
        return data.get(key);
    }

    public void putCache(String key, Object object) {
        data.put(key, object);
    }

    public void updateCache(String identityId, List<Tag> tmp) {
        AB.put(identityId + "-B", AB.get(identityId + "-A"));
        data.put(identityId, tmp);
    }

    public ReentrantReadWriteLock getRWLock(String identity) {
        ReentrantReadWriteLock lock = locks.get(identity);
        if (lock == null) {
            synchronized (this) {   //防止堵在if里面的冲进来又重设一个锁
                lock = locks.get(identity);
                if (lock == null) {
                    locks.put(identity, new ReentrantReadWriteLock());
                }
            }
        }
        return locks.get(identity);
    }

    /**
     * 出现了修改行为,需要更新缓存
     */
    public void updateAbValue(String identity) {
        identity += "-A";
        AB.put(identity, (this.getAbValue(identity) + 1));
        log.debug("更新了键:" + identity + " 的A值");
    }

    //这个synchronized不能省,效率的话,看看再说
    public synchronized Integer increPageView(Long articleId) {
        //如果是第一次访问,直接返回put的返回值,会返回一个null,页面上显示空白
        Integer t = pageViews.computeIfAbsent(articleId, k -> 1);
        return pageViews.put(articleId, t + 1);
    }

    public Integer getPageViewById(Long articleId) {
        return pageViews.get(articleId);
    }

}
