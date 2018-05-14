package com.parcel.distribution.db.repository;

import com.parcel.distribution.db.entity.Recipient;
import com.parcel.distribution.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipientRepository extends JpaRepository<Recipient, Integer> {

    List<Recipient> findAllByUser(User user);

    Recipient findById(int id);
}
