package com.postal.microservice.composite;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.http.HttpMethod.*;

//@EnableWebFluxSecurity
public class SecurityConfig {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll();
//    }

//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
////        http
////                .authorizeExchange()
////                .pathMatchers("/**")
////                .permitAll();
////        return http.build();
//
//        http
//                .authorizeExchange()
//                .anyExchange().permitAll();
//        return http.build();
//    }

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .pathMatchers("/actuator/**").permitAll()
                .pathMatchers(POST, "/address/**").hasAuthority("SCOPE_product:write")
                .pathMatchers(DELETE, "/address/**").hasAuthority("SCOPE_product:write")
                .pathMatchers(GET, "/address/**").hasAuthority("SCOPE_product:read")
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }


// disable basic auth in webflux using jwt
// @Bean
//    public SecurityWebFilterChain configure(ServerHttpSecurity http) {
//
//        http
//        .authorizeExchange().anyExchange().authenticated().and()
//            .httpBasic().disable()
//            .formLogin().disable()
//            .logout().disable()
//            .oauth2ResourceServer()
//            .jwt()
//            .and()
//                .and().exceptionHandling().accessDeniedHandler(problemSupport);
//        return http.build();
//    }
//}

}



//http.authenticateRequest().antMatcher("/**").permitAll();

