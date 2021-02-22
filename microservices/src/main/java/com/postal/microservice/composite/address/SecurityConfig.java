package com.postal.microservice.composite.address;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http
//                .authorizeExchange()
//                .pathMatchers("/actuator/**").permitAll()
//                .pathMatchers(POST, "/address/**").hasAuthority("SCOPE_product:write")
//                .pathMatchers(DELETE, "/address/**").hasAuthority("SCOPE_product:write")
//                .pathMatchers(GET, "/address/**").hasAuthority("SCOPE_product:read");
//                .anyExchange().authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
        return http.build();
    }
}