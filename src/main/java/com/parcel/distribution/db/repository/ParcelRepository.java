package com.parcel.distribution.db.repository;

import com.parcel.distribution.db.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelRepository  extends JpaRepository<Parcel, Integer> {
}
