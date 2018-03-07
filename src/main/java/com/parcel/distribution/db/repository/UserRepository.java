package com.parcel.distribution.db.repository;

import com.parcel.distribution.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);

    User findByEmail(String email);
}
