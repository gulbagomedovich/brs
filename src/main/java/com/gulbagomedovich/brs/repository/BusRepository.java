package com.gulbagomedovich.brs.repository;

import com.gulbagomedovich.brs.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    Bus findByCode(String code);

}
