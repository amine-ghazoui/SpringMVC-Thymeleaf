package org.example.springmvcthymeleaf;

import org.example.springmvcthymeleaf.entities.Patient;
import org.example.springmvcthymeleaf.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class SpringMvcThymeleafApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringMvcThymeleafApplication.class, args);
    }

    @Bean
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

    @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {

        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {
            jdbcUserDetailsManager.createUser(
                    User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build()
            );

            jdbcUserDetailsManager.createUser(
                    User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build()
            );

            jdbcUserDetailsManager.createUser(
                    User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build()
            );
        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
