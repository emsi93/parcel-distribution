package com.parcel.distribution.db.repository;

import com.parcel.distribution.db.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepository extends JpaRepository<Recipient, Integer> {
}
