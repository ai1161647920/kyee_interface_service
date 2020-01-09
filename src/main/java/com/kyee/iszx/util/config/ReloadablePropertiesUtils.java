package com.kyee.iszx.util.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.core.io.ClassPathResource;

/**
 * 可重新加载的配置项文件工具
 */
public class ReloadablePropertiesUtils {
    
    private Properties props = new Properties();
    
    /** 配置文件名称（必须放在ClassPath） */
    private String defaultPropertiesFileName = "global-config.properties";
    
    private ClassPathResource resource = new ClassPathResource(defaultPropertiesFileName);
    
    private String defaultCharset = "UTF-8";
    
    // 最后一次加载配置文件时间
    private volatile long lastLoadedTimeMillis = -1;
    /** 配置文件刷新时间间隔（单位：秒） */
    private final long refreshIntervalSeconds = 10;
    // 下一次刷新时间
    private volatile AtomicLong nextRefreshTimeMillis = new AtomicLong(-1);
    
    private Object lock = new Object();

    /** 单例模式 */
    public static ReloadablePropertiesUtils getInstance() {
        return ReloadablePropertiesUtilsHolder.instance;
    }

    private static class ReloadablePropertiesUtilsHolder {
        private static final ReloadablePropertiesUtils instance = new ReloadablePropertiesUtils();
    }
    
    /*
     * 加载Properties文件
     */
    private Properties load(String fileName, String charset) {
        Properties properties = new Properties();    
        InputStream is = ReloadablePropertiesUtils.class.getClassLoader().getResourceAsStream(fileName);
        BufferedReader bf = new BufferedReader(new InputStreamReader(is, Charset.forName(charset)));
        try {
            properties.load(bf);
        } catch (IOException e) {
        }
        return properties;
    }
    
    /**
     * 获取配置项值
     */
    public String getProperty(String key) {
        reload();
        return props.getProperty(key);
    }
    
    /**
     * 获取配置项值(有默认值)
     */
    public String getProperty(String key, String defaultValue) {
        reload();
        return props.getProperty(key, defaultValue);
    }
    
    /*
     * 是否需要刷新 
     */
    private boolean needRefresh(long currentTimeMillis, long lastLoadedTimeMillis) {
        long nextRefreshTimeMillisTemp = nextRefreshTimeMillis.get();
        if (currentTimeMillis > nextRefreshTimeMillisTemp) {
            try {
                if (resource.lastModified() > lastLoadedTimeMillis) {
                    return true;
                }
            } catch (IOException e) {
            }
            nextRefreshTimeMillis.compareAndSet(nextRefreshTimeMillisTemp, nextRefreshTimeMillisTemp + refreshIntervalSeconds * 1000);
        }
        return false;
    }
    
    /*
     * 重新加载
     */
    private void reload() {
        long currentTimeMillis = System.currentTimeMillis();
        // 采用Double-check模型，避免直接使用锁引起的性能降低
        if (needRefresh(currentTimeMillis, lastLoadedTimeMillis)) {
            synchronized(lock) {
                if (needRefresh(currentTimeMillis, lastLoadedTimeMillis)) {
                    props = load(defaultPropertiesFileName, defaultCharset);
                    lastLoadedTimeMillis = currentTimeMillis;
                    nextRefreshTimeMillis.set(lastLoadedTimeMillis + refreshIntervalSeconds * 1000);
                }
            }
        }
    }
    
}
