package com.allianz.commercespringboot.database.repository;
import com.allianz.commercespringboot.database.entity.OrderEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAll();

    List<OrderEntity> findAllByCustomerNameIgnoreCase(String customerName);

    @Modifying
    @Transactional
    void deleteById(Long id);

    Optional<OrderEntity> findById(Long id);
}
