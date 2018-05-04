package com.parcel.distribution.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "parcel_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})}, schema = "myapp")
public class ParcelInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "x", nullable = false)
    private Double x;

    @Column(name = "y", nullable = false)
    private Double y;

    @Column(name = "z", nullable = false)
    private Double z;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "date_of_delivery")
    private LocalDateTime dateOfDelivery;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parcelInfo")
    private Parcel parcel;
}
