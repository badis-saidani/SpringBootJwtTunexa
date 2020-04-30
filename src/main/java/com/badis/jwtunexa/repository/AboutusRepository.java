package com.badis.jwtunexa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.badis.jwtunexa.model.Aboutus;

@Repository
public interface AboutusRepository extends JpaRepository<Aboutus, Long> {

}
