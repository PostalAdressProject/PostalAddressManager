package com.postal.microservices;

//import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.ObjectPostProcessor;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

public class PostalSecurity {

}
//@Configuration
//@EnableWebFluxSecurity
//@ComponentScan({"com.postal"})
//public class PostalSecurity {
//
////    @Bean
////    ObjectPostProcessor objectPostProcessor(){
////        return new ObjectPostProcessor() {
////            @Override
////            public Object postProcess(Object object) {
////                return null;
////            }
////        };
////    }
//
////    @Bean
////    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
////        return http
////                .authorizeExchange()
////                .matchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
////                .pathMatchers("/address/**", "/actuator/**")
////                .authenticated()
////                .and()
////                .formLogin().and()
////                .build();
////    }
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
//        http
//                .authorizeExchange()
//                .pathMatchers("/address/**")
//                .permitAll()
//                .anyExchange()
//                .authenticated()
//                .and()
//                .httpBasic();
////                .and()
////                .exceptionHandling().accessDeniedPage("/403");
////        http.authorizeExchange().anyExchange().authenticated().and().oauth2Login().and().oauth2Client();
//        return http.build();
//    }
//}
