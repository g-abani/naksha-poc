package com.adobe.nakshapoc.sysconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.adobe.nakshapoc.filter.MSSecuirtyFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private MSSecuirtyFilter filter;

    @Bean
    public SecurityFilterChain securityChainFilter(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request) ->
            request.requestMatchers("/hallo-anonymous","/generate/*", "fetch", "/config").permitAll()
            .anyRequest().authenticated());
        http.csrf((csrf) -> csrf.disable())
        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
