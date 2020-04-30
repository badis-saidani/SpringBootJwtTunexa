package com.badis.jwtunexa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.badis.jwtunexa.model.Devis;

@Repository
public interface DevisRepository extends JpaRepository<Devis, Long>  {

}
