package org.example.springmvcthymeleaf;

import org.example.springmvcthymeleaf.entities.Patient;
import org.example.springmvcthymeleaf.repositories.PatientRepository;
import org.example.springmvcthymeleaf.sec.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class SpringMvcThymeleafApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringMvcThymeleafApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {

            patientRepository.save(new Patient(null, "hassane", new Date(), false, 12));
            patientRepository.save(new Patient(null, "karim", new Date(), true, 120));
            patientRepository.save(new Patient(null, "jaouad", new Date(), true, 67));
            patientRepository.save(new Patient(null, "hmad", new Date(), false, 87));

            patientRepository.findAll().forEach(p ->
                    System.out.println(p.toString())
            );
        };
    }
    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {

        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {

            try {
                jdbcUserDetailsManager.loadUserByUsername("user11");
            }
            catch (UsernameNotFoundException e) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            }

            try {
                jdbcUserDetailsManager.loadUserByUsername("user22");
            } catch (UsernameNotFoundException e) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            }

            try {
                jdbcUserDetailsManager.loadUserByUsername("admin2");
            } catch (UsernameNotFoundException e) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build()
                );
            }
        };
    }

    @Bean
    CommandLineRunner commandLineRunner(AccountService accountService) {
        return args -> {
            // dans ce cas on utilisé des méthodes personnalisées
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user1", "1234", "user1123@gmail.com", "1234");
            accountService.addNewUser("user2", "1234", "user2123@gmail.com", "1234");
            accountService.addNewUser("admin", "1234", "admin123@gmail.com", "1234");

            accountService.addRoleToUser("user1", "USER");
            accountService.addRoleToUser("user2", "USER");
            accountService.addRoleToUser("admin", "ADMIN");
            accountService.addRoleToUser("admin", "USER");
        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
