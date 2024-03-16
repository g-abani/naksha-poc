package com.adobe.nakshapoc.svc;

import com.adobe.nakshapoc.dto.UserPrincipal;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ValidateToken {

    private static final Logger LOGGER = LoggerFactory.getLogger( ValidateToken.class );

    @Value("${microsoft.graph.me:https://graph.microsoft.com/v1.0/me}")
    private String url;

    public Authentication getTokenInfo(final String token){
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Authorization", "Bearer " + token);
        try(CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpGet)){
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == 200){
                    String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
                    JsonParser parser = JsonParserFactory.getJsonParser();
                    Map<String, Object> map = parser.parseMap(responseBody);
                    
                    UserPrincipal userPrincipal = new UserPrincipal(map.get("id").toString(), true);
                    userPrincipal.setEmail(map.get("mail").toString());
                    userPrincipal.setDisplayName(map.get("displayName").toString());
                    return userPrincipal;
                }
        }catch(Exception e){
            LOGGER.error("Error while validating token", e);
        }
        return null;
    }

}
