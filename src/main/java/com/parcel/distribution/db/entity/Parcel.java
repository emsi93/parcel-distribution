package com.parcel.distribution.db.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "parcel", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "code", nullable = false, length = 4)
    private String code;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient_id", nullable = false)
    private Recipient recipient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parcel_info_id", nullable = false)
    private ParcelInfo parcelInfo;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;
}
