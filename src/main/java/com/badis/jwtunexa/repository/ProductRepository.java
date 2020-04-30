package com.badis.jwtunexa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.badis.jwtunexa.model.FileModel;
import com.badis.jwtunexa.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategoryId(Long id);
}
