package com.adobe.nakshapoc.properties;

import com.adobe.nakshapoc.repository.CfgRepository;
import com.adobe.nakshapoc.svc.ExtProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("cfgAwareProperties")
public class CfgAwareProperties implements EnvironmentAware {

    @Autowired
    private CfgRepository cfgRepository;

    @Autowired
    private List<ExtProperty> extProperties;

    @Override
    public void setEnvironment(Environment environment) {
        ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) environment;
        Map<String, Object> propertySource = new HashMap<>();
        extProperties.forEach(extProperty -> propertySource.putAll(extProperty.getProperties()));
        this.cfgRepository.findAll().stream().forEach(config -> propertySource.put(config.getPropertyKey(), config.getPropertyValue()));
        configurableEnvironment.getPropertySources().addAfter("systemEnvironment", new MapPropertySource("db-config", propertySource));
    }
}
