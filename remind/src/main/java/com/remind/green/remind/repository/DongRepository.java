package com.remind.green.remind.repository;

import com.remind.green.remind.entity.Dongsamuso;
import org.springframework.data.jpa.repository.JpaRepository;

//ans2> 리포지토리를 만들어라
public interface DongRepository extends JpaRepository<Dongsamuso, Long> {
}

