package academy.devdojo.webflux.config;

import academy.devdojo.webflux.service.DevDojoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        //@formatter:off
        return http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.POST, "/anime/**").hasRole("ADMIN")
                .pathMatchers(HttpMethod.PUT, "/anime/**").hasRole("ADMIN")
                .pathMatchers(HttpMethod.DELETE, "/anime/**").hasRole("ADMIN")
                .pathMatchers(HttpMethod.GET, "/anime/**").hasRole("USER")
                .pathMatchers("/webjars/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .anyExchange().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .build();
        //@formatter:on
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager(DevDojoUserDetailsService devDojoUserDetailsService) {
        return new UserDetailsRepositoryReactiveAuthenticationManager(devDojoUserDetailsService);
    }

//    @Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder.encode("devdojo"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder.encode("devdojo"))
//                .roles("USER","ADMIN")
//                .build();
//
//        return new MapReactiveUserDetailsService(user,admin);
//    }
}
