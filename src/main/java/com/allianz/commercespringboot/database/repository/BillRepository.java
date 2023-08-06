package com.allianz.commercespringboot.database.repository;

import com.allianz.commercespringboot.database.entity.BillEntity;
import com.allianz.commercespringboot.database.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {
    List<BillEntity> findAll();

}
