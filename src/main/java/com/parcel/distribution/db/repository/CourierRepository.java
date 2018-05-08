package com.parcel.distribution.db.repository;

import com.parcel.distribution.db.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository  extends JpaRepository<Courier, Integer> {

    Courier findByLogin(String login);

    Courier findByEmail(String email);

    Courier findByLoginAndPasswordAndActive(String login, String password, boolean active);
}
