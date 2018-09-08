package com.parcel.distribution.db.repository;

import com.parcel.distribution.db.entity.Parcel;
import com.parcel.distribution.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParcelRepository extends JpaRepository<Parcel, Integer> {

    List<Parcel> findAllByUser(User user);

    Parcel findById(int id);

    Parcel findByIdAndStatus(int id, String status);

//    @Query("select p from parcel p where p.id = :idparcel and ( p.status = :start or p.status = :end )")
//    Parcel findByIdAndBetweenStatus(@Param("idparcel") int id,
//                                    @Param("start") int start,
//                                    @Param("end") int end);
}
