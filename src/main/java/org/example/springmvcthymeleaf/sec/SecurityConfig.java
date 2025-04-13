package org.example.springmvcthymeleaf.sec;


import lombok.AllArgsConstructor;
import org.example.springmvcthymeleaf.sec.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
// Configuration de la sécurité de l'application
@EnableWebSecurity
// Configuration de la sécurité des méthodes (annotation @PreAuthorize, @PostAuthorize, etc.)
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {

    private PasswordEncoder passwordEncoder;
    private UserDetailServiceImpl userDetailsServiceImpl;

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        //(Spécifier le data source)dans quelle DB ou se trouve les utilisateurs et les roles
        return new JdbcUserDetailsManager(dataSource);
    }

    //@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        String encodedPassword = passwordEncoder.encode("1234");
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(encodedPassword).roles("USER").build(),
                User.withUsername("user2").password(encodedPassword).roles("USER").build(),
                User.withUsername("admin").password(encodedPassword).roles("ADMIN", "USER").build()
        );
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.formLogin(form ->form.loginPage("/login").defaultSuccessUrl("/").permitAll());

        http.authorizeHttpRequests(ar -> ar.requestMatchers("/admin/**").hasRole("ADMIN"));
        http.authorizeHttpRequests(ar -> ar.requestMatchers("/user/**").hasRole("USER"));
        http.authorizeHttpRequests(ar -> ar.requestMatchers("/webjars/**", "/h2-console/**").permitAll());
        http.authorizeHttpRequests(ar -> ar.anyRequest().authenticated());
        // Gestion des exceptions
        http.exceptionHandling(eh -> eh
                .accessDeniedPage("/notAuthorized") // Redirige vers une page d'erreur 403
        );

        http.userDetailsService(userDetailsServiceImpl);

        return http.build();
    }
}
