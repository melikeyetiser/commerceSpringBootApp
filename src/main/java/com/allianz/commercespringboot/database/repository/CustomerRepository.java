package com.allianz.commercespringboot.database.repository;

import com.allianz.commercespringboot.database.entity.CustomerEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    List<CustomerEntity> findAll();

    List<CustomerEntity> findAllByNameContainingIgnoreCase(String key);

    List<CustomerEntity> findAllById(Long id);

    @Modifying
    @Transactional
    void deleteById(Long id);

    boolean existsById(Long id);

    Optional<CustomerEntity> findById(Long id);
}
