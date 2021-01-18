package com.inspire12.homepage.domain.repository;

import com.inspire12.homepage.domain.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
