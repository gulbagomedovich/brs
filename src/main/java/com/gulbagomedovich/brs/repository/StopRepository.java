package com.gulbagomedovich.brs.repository;

import com.gulbagomedovich.brs.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends JpaRepository<Stop, Long> {

    Stop findByCode(String code);

}
