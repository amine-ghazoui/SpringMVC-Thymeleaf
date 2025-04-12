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
            // Retourne l'utilisateur existant au lieu de lever une exception
            return appUser;
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
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(String role) {
        // Vérifie si le rôle existe déjà
        AppRole appRole = appRoleRepository.findById(role).orElse(null);

        if (appRole != null) {
            // Retourne le rôle existant au lieu de lever une exception
            return appRole;
        }

        // Crée un nouveau rôle si inexistant
        appRole = AppRole.builder()
                .role(role)
                .build();
        return appRoleRepository.save(appRole);
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
