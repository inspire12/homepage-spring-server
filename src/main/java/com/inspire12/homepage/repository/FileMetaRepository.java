package com.inspire12.homepage.repository;

import com.inspire12.homepage.model.entity.FileMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetaRepository extends JpaRepository<FileMeta, Integer> {
}
