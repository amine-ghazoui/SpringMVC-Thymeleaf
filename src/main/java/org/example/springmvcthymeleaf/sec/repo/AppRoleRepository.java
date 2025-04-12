package org.example.springmvcthymeleaf.sec.repo;

import org.example.springmvcthymeleaf.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {

}
