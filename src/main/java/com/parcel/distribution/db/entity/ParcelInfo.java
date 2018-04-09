package com.parcel.distribution.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "parcel_info", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class ParcelInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Double x;

    private Double y;

    private Double z;

    private Double weight;

    private LocalDateTime dataNadania;

    private LocalDateTime dataOdebrania;
}
