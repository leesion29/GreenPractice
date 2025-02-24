package com.green.company.project1.repository;

import com.green.company.project1.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository  extends JpaRepository<Score, Long> {
}
