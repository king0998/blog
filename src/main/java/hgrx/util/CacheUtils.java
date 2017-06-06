package hgrx.util;

import hgrx.bean.Tag;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by HGRX on 2017/6/5
 */
public enum CacheUtils {
    MyCache;

    Log log = LogFactory.getLog(this.getClass());

    //储存需要两个变量来标识是否更新缓存的,那两个标识
    private ConcurrentHashMap<String, Integer> AB = new ConcurrentHashMap<>();

    //缓存数据放这
    private ConcurrentHashMap<String, Object> data = new ConcurrentHashMap<>();

    //单个读写锁
    private ConcurrentHashMap<String, ReentrantReadWriteLock> locks = new ConcurrentHashMap<>();

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

    public void updateCache(String identityId, List<Tag> tmp) {
        AB.put(identityId + "-B", AB.get(identityId + "-A"));
        data.put(identityId, tmp);
    }

    public ReentrantReadWriteLock getRWLock(String identity) {
        ReentrantReadWriteLock lock = locks.get(identity);
        if (lock == null) {
            synchronized (this) {
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

}
