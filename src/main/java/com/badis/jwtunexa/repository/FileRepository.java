package com.badis.jwtunexa.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.badis.jwtunexa.model.FileModel;
import com.badis.jwtunexa.model.Product;



@Transactional
public interface FileRepository extends JpaRepository<FileModel, Long>{	
	
	Optional<FileModel> findByProductId(Long id);
	//FileModel findcc(int id);

	void deleteByProductId(Long id);
}