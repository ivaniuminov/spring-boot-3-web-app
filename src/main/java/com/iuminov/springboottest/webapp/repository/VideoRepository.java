package com.iuminov.springboottest.webapp.repository;

import com.iuminov.springboottest.webapp.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
    List<VideoEntity> findByName(String name);

    @Query("select v from VideoEntity v where v.name = ?1")
    List<VideoEntity> findCustomerReport(String name);

    @Query(value="select * from VIDEO_ENTITY where NAME = ?1",
            nativeQuery=true)
    List<VideoEntity> findCustomWithPureSql(String name);

    List<VideoEntity> findByNameContainsOrDescriptionContainsAllIgnoreCase(String name, String description);

    List<VideoEntity> findByNameContainsIgnoreCase(String name);

    List<VideoEntity> findByDescriptionContainsIgnoreCase(String description);
}
