package com.adobe.nakshapoc.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.adobe.nakshapoc.dto.UserPrincipal;
import com.adobe.nakshapoc.svc.ValidateToken;
import com.nimbusds.jose.proc.SecurityContext;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MSSecuirtyFilter extends OncePerRequestFilter{

    private static final Logger LOGGER = LoggerFactory.getLogger( MSSecuirtyFilter.class );
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private ValidateToken validateToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        if (header == null || !header.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(BEARER_PREFIX.length());
        UserPrincipal userPrincipal = (UserPrincipal) validateToken.getTokenInfo(token);
        LOGGER.debug("UserPrincipal: {}", userPrincipal);
        SecurityContextHolder.getContext().setAuthentication(userPrincipal);
        filterChain.doFilter(request, response);
    }
    
}
