package com.parcel.distribution.db.repository;

import com.parcel.distribution.db.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourierRepository  extends JpaRepository<Courier, Integer> {

    Courier findByLogin(String login);

    Courier findByEmail(String email);

    Courier findByLoginAndPasswordAndActive(String login, String password, boolean active);

    Courier findByLoginAndTokenAndActive(String login, String token, boolean active);

    List<Courier> findByActive(boolean active);
}
