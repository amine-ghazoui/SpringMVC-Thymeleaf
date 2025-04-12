package org.example.springmvcthymeleaf.sec.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.springmvcthymeleaf.sec.entities.AppRole;
import org.example.springmvcthymeleaf.sec.entities.AppUser;
import org.example.springmvcthymeleaf.sec.repo.AppRoleRepository;
import org.example.springmvcthymeleaf.sec.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    AppUserRepository appUserRepository;
    AppRoleRepository appRoleRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {

        AppUser appUser = appUserRepository.findByUsername(username);

        if (appUser != null) {
            throw new RuntimeException("User already exists");
        }

        if (!password.equals(confirmPassword)) {
            throw new RuntimeException("Passwords do not match");
        }

        appUser = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        AppUser savedAppUser =  appUserRepository.save(appUser);

        return savedAppUser;
    }

    @Override
    public AppRole addNewRole(String role) {

        AppRole appRole = appRoleRepository.findById(role).orElse(null);

        if(appRole != null) {
            throw new RuntimeException("Role already exists");
        }

        AppRole saveedAppRole = AppRole.builder()
                .role(role)
                .build();
        return saveedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String role) {

        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {

        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {

        return appUserRepository.findByUsername(username);
    }
}
