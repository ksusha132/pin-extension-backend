package com.pinext.backend.pinextensionbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "person")
public class Person {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;
    /**
     * name
     */
    @Column(name = "name")
    String name;
    /**
     * email
     */
    @Column(name = "email")
    String email;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Subscription> subscriptions;
}
