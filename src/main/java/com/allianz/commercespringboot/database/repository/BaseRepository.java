package com.allianz.commercespringboot.database.repository;

import com.allianz.commercespringboot.util.dbutil.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@NoRepositoryBean
public interface BaseRepository extends JpaRepository<BaseEntity, Long> {
    /*
    List<BaseEntity> findAll();

    List<BaseEntity> findAllByNameContaining(String key);

    List<BaseEntity> findAllByUuid(UUID uuid);
*/
}
