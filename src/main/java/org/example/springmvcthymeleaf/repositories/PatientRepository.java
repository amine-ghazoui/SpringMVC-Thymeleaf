package org.example.springmvcthymeleaf.repositories;

import org.example.springmvcthymeleaf.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {


}
