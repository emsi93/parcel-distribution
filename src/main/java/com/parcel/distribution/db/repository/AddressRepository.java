package com.parcel.distribution.db.repository;

import com.parcel.distribution.db.entity.Address;
import com.parcel.distribution.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Address findByUser(User user);
}
