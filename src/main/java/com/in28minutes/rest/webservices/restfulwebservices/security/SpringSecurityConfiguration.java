package com.in28minutes.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //to authenticate all requests:
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());

        //enable basic authentication:
        http.httpBasic(withDefaults());

        // to disable CSRF
        http.csrf().disable();

        return http.build();
    }


}
