package com.postal.microservices;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

// minimal WebFlux Security configuration
//@Configuration
//@EnableWebSecurity
@ComponentScan
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("pass")
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }


    private ObjectPostProcessor objectPostProcessor;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private SecurityContextRepository securityContextRepository;

//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return super.userDetailsService();
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER").and()
//                .withUser("admin").password("password").roles("USER","ADMIN");
//    }


    /**
     * The default {@link ServerHttpSecurity} configuration.
     * @param http
     * @return
     */
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {

        // Add custom security.
//        http.authenticationManager((ReactiveAuthenticationManager) this.authenticationManager);
//        http.securityContextRepository((ServerSecurityContextRepository) this.securityContextRepository);

        http
                .authorizeExchange()
                .pathMatchers("/**")
                .permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .httpBasic().and()
                .formLogin();
//        http.authorizeExchange().anyExchange().authenticated().and().oauth2Login().and().oauth2Client();
        return http.build();
    }

}
