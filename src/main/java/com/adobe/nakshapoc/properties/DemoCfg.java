package com.adobe.nakshapoc.properties;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DemoCfg {
    @Value("${app.name:Abani}")
    private String appName;

    @PostConstruct
    void init(){
        System.out.println("DemoCfg initialized " + this.appName);
    }
}
