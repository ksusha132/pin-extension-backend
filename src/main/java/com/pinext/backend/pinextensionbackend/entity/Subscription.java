package com.pinext.backend.pinextensionbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

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
    String id;

    /**
     * type
     */
    @Column(name = "type")
    String type;

    /**
     * state
     */
    @Column(name = "state")
    String state;

    /**
     * active
     */
    @Column(name = "active")
    Boolean active;

    /**
     * date from
     */
    @Column(name = "sfrom")
    LocalDate from;

    /**
     * date to
     */
    @Column(name = "sto")
    LocalDate to;

    @ManyToOne
    @JoinColumn(name = "person", nullable = false)
    private Person person;
}
