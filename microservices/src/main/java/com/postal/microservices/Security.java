package com.postal.microservices;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class Security {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {

        http
                .authorizeExchange()
                .pathMatchers("/address/**")
                .permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .httpBasic();
//                .and()
//                .exceptionHandling().accessDeniedPage("/403");
//        http.authorizeExchange().anyExchange().authenticated().and().oauth2Login().and().oauth2Client();
        return http.build();
    }
}
