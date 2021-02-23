package com.postal.microservice.composite;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Create creds for security
 */
@EnableWebFluxSecurity
public class SecurityConfig {
//    @Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("pass")
//                .roles("USER")
//                .build();
//        return new MapReactiveUserDetailsService(user);
//    }

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http
//                .authorizeExchange()
//                .pathMatchers("/actuator/**").permitAll()
//                .pathMatchers(POST, "/address/**").hasAuthority("SCOPE_product:write")
//                .pathMatchers(DELETE, "/address/**").hasAuthority("SCOPE_product:write")
//                .pathMatchers(GET, "/address/**").hasAuthority("SCOPE_product:read")
//                .anyExchange().authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt();

        http
                .authorizeExchange()
                .pathMatchers("/**").permitAll()
                .anyExchange().authenticated();
        //.and()
        //.oauth2ResourceServer() don't use it,
        //.jwt();
        // Do not use it, otherwise you must define
        // jwtDecoderByIssuerUri or jwtDecoderByJwkKeySetUri

        return http.build();
    }
}
