package com.allianz.commercespringboot.database.repository;

import com.allianz.commercespringboot.database.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAll();

    List<ProductEntity> findAllByNameContainingIgnoreCase(String key);

    @Modifying
    @Transactional
    void deleteById(Long id);

    ProductEntity findByID(Long id);

    ProductEntity findByNameIgnoreCase(String name);
}
