package com.pinext.backend.pinextensionbackend.repository;

import com.pinext.backend.pinextensionbackend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;


public interface PersonRepository extends JpaRepository<Person, UUID> {
    @Query("select s.active " +
            "from Person p " +
            "join Subscription s on p.id = s.person.id " +
            "where p.email = :email and s.type = :type")
    Boolean findByEmailAndType(String email, String type);

    Person findByEmail(String email);
}
