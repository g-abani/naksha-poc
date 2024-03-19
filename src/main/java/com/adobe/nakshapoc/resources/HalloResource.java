package com.adobe.nakshapoc.resources;

import com.adobe.nakshapoc.svc.Caching;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HalloResource {

    @Autowired
    private Caching caching;

    @GetMapping("hallo")
    public String sayHallo(HttpServletRequest request) {
        return new StringBuilder("Hallo ").
                append( request.getUserPrincipal().getName() ).toString();
    }

    @GetMapping("hallo-anonymous")
    public String sayHalloAnonymous() {
        return "Hallo Anonymous " + caching.getListOpsSize();
    }

    @GetMapping("generate/{id}")
    public void generate(@PathVariable("id") String id) {
        caching.addUri( "urls", id );
    }

    @GetMapping("fetch")
    public long fetch() {
        return caching.fetchPartial();
    }

}
