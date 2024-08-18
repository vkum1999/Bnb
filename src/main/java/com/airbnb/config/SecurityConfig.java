package com.airbnb.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;


@Configuration
public class  SecurityConfig {

    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        //hcd2
        http.csrf().disable().cors().disable();

        //haap
//        http.authorizeHttpRequests().anyRequest().permitAll();
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/createuser","/api/v1/auth/createPropertyowner",
                        "/api/v1/auth/login","/api/v1/auth/createPropertymanger","/api/v1/country/**")
                .permitAll()
                .requestMatchers("/api/v1/property/addProperty").hasAnyRole("OWNER","ADMIN")
                .anyRequest()
                .authenticated()
        ;
        return http.build();
    }

}
