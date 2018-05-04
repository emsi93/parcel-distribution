package com.parcel.distribution.db.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})}, schema = "myapp")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

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

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address() {

    }
}
