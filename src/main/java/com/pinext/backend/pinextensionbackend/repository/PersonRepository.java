package com.pinext.backend.pinextensionbackend.repository;

import com.pinext.backend.pinextensionbackend.entity.Person;
import com.pinext.backend.pinextensionbackend.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String> {
    @Query("select s.subscriptionId " +
            "from Person p " +
            "join Subscription s on p.id = s.person.id " +
            "where p.email = :email and s.type = :type and s.state = :state")
    String findByEmailAndTypeAndState(String email, String type, String state);

    Person findAllByEmail(String email);

    Optional<Person> findByFastSpringId(String id);
}
