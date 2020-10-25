package com.pinext.backend.pinextensionbackend.repository;

import com.pinext.backend.pinextensionbackend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
}
