package com.parcel.distribution.db.repository;

import com.parcel.distribution.db.entity.Parcel;
import com.parcel.distribution.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParcelRepository  extends JpaRepository<Parcel, Integer> {

    List<Parcel> findAllByUserAndStatus(User user, boolean status);

    Parcel findById(int id);

    Parcel findByIdAndStatus(int id, boolean status);
}
