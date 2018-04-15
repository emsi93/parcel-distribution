package com.parcel.distribution.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "courier", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "login", nullable = false, unique = true, length = 20)
    private String login;

    @Column(name = "password", nullable = false, length = 90)
    private String password;

    @Column(name = "email", nullable = false, unique = true, length = 30)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 12)
    private String phoneNumber;

    @Column(name = "active", nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "courier")
    private List<Parcel> parcelList;
}
