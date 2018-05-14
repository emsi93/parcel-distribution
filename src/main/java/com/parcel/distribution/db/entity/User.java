package com.parcel.distribution.db.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "surname", length = 50)
    private String surname;

    @Column(name = "login", nullable = false, unique = true, length = 20)
    private String login;

    @Column(name = "email", nullable = false, unique = true, length = 30)
    private String email;

    @Column(name = "password", nullable = false, length = 90)
    private String password;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "role", nullable = false, length = 20)
    private String role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<Parcel> parcelList;

    @OneToMany(mappedBy = "user")
    private List<Recipient> recipientList;

    public User() {

    }
}
