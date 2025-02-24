package com.green.company.project1.Service;

import com.green.company.project1.domain.Score;
import com.green.company.project1.dto.ScoreDTO;
import com.green.company.project1.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService{
    private final ModelMapper modelMapper;
    private final ScoreRepository scoreRepository;

    //추상 메서드 구현

    // 데이터를 추가
    @Override
    public Long post(ScoreDTO scoreDTO){
        Score sc = modelMapper.map(scoreDTO, Score.class);
        Score saveSC = scoreRepository.save(sc);
        return saveSC.getSno();
    }

    // 데이터를 조회
    @Override
    public ScoreDTO get(Long sno){
        Optional<Score> res = scoreRepository.findById(sno);
        Score sc = res.orElseThrow();
        ScoreDTO dto = modelMapper.map(sc, ScoreDTO.class);
        return dto;
    }

    // 데이터를 수정
    @Override
    public void put(ScoreDTO scoreDTO){
        Optional<Score> res = scoreRepository.findById(scoreDTO.getSno());

        Score sc = res.orElseThrow();
        sc.changeMath(scoreDTO.getMath());
        sc.changeEng(scoreDTO.getEng());
        sc.changeKorea(scoreDTO.getKorea());

        scoreRepository.save(sc);
    }
}
