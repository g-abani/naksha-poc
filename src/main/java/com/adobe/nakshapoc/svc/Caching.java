package com.adobe.nakshapoc.svc;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class Caching {

    private static final Logger LOGGER = LoggerFactory.getLogger(Caching.class);

    @Resource(name = "redisTemplate")
    private SetOperations<String, String> setOps;


    public void addUri(String key, String uri) {
        Lock lock = new ReentrantLock();
        lock.lock();
        setOps.add(key, uri);
        lock.unlock();
    }

    public long getListOpsSize() {
        return this.setOps.size("urls");
    }

    public long fetchPartial() {
        setOps.pop("urls", 100);
        return this.setOps.size("urls");
    }

    public List<String> fetchPartial(long count){
        return this.setOps.pop("urls", count);
    }
}
