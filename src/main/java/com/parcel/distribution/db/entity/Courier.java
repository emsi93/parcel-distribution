package com.parcel.distribution.db.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "courier", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "surname", length = 50)
    private String surname;
}
