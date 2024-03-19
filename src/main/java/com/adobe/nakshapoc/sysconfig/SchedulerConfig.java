package com.adobe.nakshapoc.sysconfig;

import com.adobe.nakshapoc.svc.Caching;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@EnableScheduling
@Component
public class SchedulerConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerConfig.class);

    @Autowired
    private Caching caching;

    @Scheduled(fixedRate = 300000, initialDelay = 1000)
    public void reportCurrentTime() {
        LOGGER.info("list {} ",caching.fetchPartial(100));
    }

}
