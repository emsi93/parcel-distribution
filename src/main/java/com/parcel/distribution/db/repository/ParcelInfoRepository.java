package com.parcel.distribution.db.repository;

import com.parcel.distribution.db.entity.ParcelInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelInfoRepository extends JpaRepository<ParcelInfo, Integer> {
}
