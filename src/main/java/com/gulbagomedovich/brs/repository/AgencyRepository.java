package com.gulbagomedovich.brs.repository;

import com.gulbagomedovich.brs.model.Agency;
import com.gulbagomedovich.brs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {

    Agency findByCode(String code);

    Agency findByName(String name);

    Agency findByOwner(User owner);

}
