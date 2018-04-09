package com.parcel.distribution.db.repository;

import com.parcel.distribution.db.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository  extends JpaRepository<Courier, Integer> {
}
