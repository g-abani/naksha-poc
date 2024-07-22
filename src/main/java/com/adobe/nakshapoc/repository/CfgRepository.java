package com.adobe.nakshapoc.repository;

import com.adobe.nakshapoc.entity.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CfgRepository extends JpaRepository<Configuration, Long> {
    Configuration findByPropertyKey(String key);
}
