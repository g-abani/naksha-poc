package com.adobe.nakshapoc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties(prefix = "microsoft.graph")
public class AuthCfg {
    private String me;

    public String getMe() {
        return me;
    }
    
    public void setMe(String me) {
        this.me = me;
    }
}
