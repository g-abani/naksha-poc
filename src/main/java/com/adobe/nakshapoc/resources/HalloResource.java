package com.adobe.nakshapoc.resources;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HalloResource {

    @GetMapping("hallo")
    public String sayHallo(HttpServletRequest request) {
        return new StringBuilder("Hallo ").
                append( request.getUserPrincipal().getName() ).toString();
    }

    @GetMapping("hallo-anonymous")
    public String sayHalloAnonymous() {
        return "Hallo Anonymous";
    }

}
