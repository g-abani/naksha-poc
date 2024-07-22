package com.adobe.nakshapoc.resources;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${state:Abani}")
    private String appName;

    @GetMapping("/config")
    public String getConfig() {
        return appName;
    }

}
