package org.example.springmvcthymeleaf.sec;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.formLogin(Customizer.withDefaults());
        http.authorizeHttpRequests(ar -> ar.requestMatchers("/admin/**").hasRole("ADMIN"));
        http.authorizeHttpRequests(ar -> ar.requestMatchers("/user/**").hasRole("USER"));
        http.authorizeHttpRequests(ar -> ar.requestMatchers("/webjars/**", "/h2-console/**").permitAll());
        http.authorizeHttpRequests(ar -> ar.anyRequest().authenticated());

        return http.build();
    }
}
