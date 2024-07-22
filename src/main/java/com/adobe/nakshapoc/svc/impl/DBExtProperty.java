package com.adobe.nakshapoc.svc.impl;

import com.adobe.nakshapoc.svc.ExtProperty;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DBExtProperty implements ExtProperty {
    @Override
    public Map<String, Object> getProperties() {
        return Map.of("name","Abani", "age", 25);
    }
}
