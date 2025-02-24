package com.green.company.project1.Service;

import com.green.company.project1.dto.ScoreDTO;

public interface ScoreService {
    // 데이터 추가
    Long post(ScoreDTO scoreDTO);
    // 데이터 조회
    ScoreDTO get(Long sno); //get
    // 데이터 수정
    void put(ScoreDTO scoreDTO); //modify
}
