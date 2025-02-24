package com.green.company.project1.repository;

import com.green.company.project1.domain.Score;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class ScoreRepositoryTest {

    @Autowired
    private ScoreRepository repository;

    // 저장
    @Test
    public void create(){
        int eng = 10;
        int math = 10;
        int korea = 10;
        int sum = 0;
        String grade = "";
        for(int i = 1; i <=5; i++){
            sum = eng+math+korea;
            float avg = eng+math+korea/3.0f;
            avg = (float) (Math.round(avg/10.0f) * 10.0);
            if(sum > 200){grade = "우수";}
            else {grade = "미흡";}
            Score sc = Score.builder()
                    .eng(eng)
                    .math(math)
                    .korea(korea)
                    .total(sum)
                    .avg(avg)
                    .grade(grade)
                    .build();

            repository.save(sc);
            log.info("생성했다");
        }
    }

    @Test
    public void read(){
        Long sno = 41l;
        Optional<Score> result = repository.findById(sno);
        Score sc = result.orElseThrow();
        log.info("읽었다: " +sc);
    }

    @Test // DB 내 값이 안 바뀜
    @Transactional
    public void Modify(){
        Long sno = 41l;
        Optional<Score> result = repository.findById(sno);
        Score sc = result.orElseThrow();
        sc.changeMath(1);
        sc.changeEng(1);
        sc.changeKorea(1);
        log.info("점수 바꾸다 -> " + String.valueOf(sc.getMath()) +
                " " +String.valueOf(sc.getMath()) + " " +String.valueOf(sc.getKorea()));
        repository.save(sc);
    }
}
