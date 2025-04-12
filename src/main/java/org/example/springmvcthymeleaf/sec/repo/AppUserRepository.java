package org.example.springmvcthymeleaf.sec.repo;

import org.example.springmvcthymeleaf.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

    AppUser findByUsername(String username);
}
