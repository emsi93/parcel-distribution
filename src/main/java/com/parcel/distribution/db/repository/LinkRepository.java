package com.parcel.distribution.db.repository;

import com.parcel.distribution.db.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Integer> {

    long countByLink(String link);

    long countByEmailAndType(String email, String type);

    Link findByLink(String link);

    Link findByLinkAndType(String link, String type);

    Link findByEmailAndType(String email, String type);

    void deleteByEmailAndType(String email, String type);
}
