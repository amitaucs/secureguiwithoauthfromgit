package com.amisoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by amitdatta on 03/05/17.
 */

@Controller
@EnableOAuth2Sso
public class secureguiwithoauthcontroller extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2ClientContext clientContext;

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    @RequestMapping("/")
    public String loadHomePage() {

        return "homepage";
    }


    @RequestMapping("/loademployee")
    public String loadEmployssData(Model model) {

        OAuth2AccessToken token = clientContext.getAccessToken();
        System.out.println("Token :" + token);

        ResponseEntity<List<EmployeeDetails>> employeelist = oAuth2RestTemplate.exchange("http://localhost:9001/services/getemployee",
                                                                                            HttpMethod.GET,null,
                                                                                              new ParameterizedTypeReference<List<EmployeeDetails>>() {});
        model.addAttribute("employee",employeelist.getBody());

        return "employeepage";
    }


    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/", "/login**").permitAll().anyRequest().authenticated();
    }
}
