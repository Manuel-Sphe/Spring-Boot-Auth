package dev.sphe.jwtdemo.confing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   @Bean
   public InMemoryUserDetailsManager user(){
       return new InMemoryUserDetailsManager(
               User.withUsername("sphe")
                       .password("{noop}password")
                       .authorities("read")
                       .build()
       );
   }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return  http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth ->auth
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(withDefaults())
                .build();
    }
}
