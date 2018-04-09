package com.parcel.distribution.db.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "parcel", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "description", nullable = false )
    private String description;

    @Column(name = "code")
    private String code;



}
