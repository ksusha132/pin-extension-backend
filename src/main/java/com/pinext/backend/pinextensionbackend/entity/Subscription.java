package com.pinext.backend.pinextensionbackend.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Участники
 */
@Data
@Entity
@Table(name = "subscription")
public class Subscription {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * type
     */
    @Column(name = "type")
    String type;

    /**
     * active
     */
    @Column(name = "active")
    Boolean active;

    @ManyToOne
    @JoinColumn(name = "person", nullable = false)
    private Person person;
}
