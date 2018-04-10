package com.parcel.distribution.db.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "recipient", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 12)
    private String phoneNumber;

    @Column(name = "street", nullable = false, length = 50)
    private String street;

    @Column(name = "street_number", nullable = false, length = 5)
    private String streetNumber;

    @Column(name = "flat_number", length = 5)
    private String flatNumber;

    @Column(name = "post_code", nullable = false, length = 6)
    private String postCode;

    @Column(name = "city", nullable = false, length = 30)
    private String city;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "recipient")
    private Parcel parcel;
}
