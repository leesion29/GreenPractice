package com.remind.green.remind.controller;


import com.remind.green.remind.dto.ScoreDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/basic")
public class YellowController {
    List<ScoreDTO> list = new ArrayList<>();
    private static int no = 0;
    @PostMapping
    public ResponseEntity<List<ScoreDTO>> home(@RequestBody ScoreDTO dto) {
        int e = dto.getEng();
        int m = dto.getMath();
        int k = dto.getKorea();
        int total = e + m + k;
        int avg = total / 3;

        String grade = "";
        if (avg >= 90) {
            grade = "A";
        } else if (avg >= 80) {
            grade = "B";
        } else if (avg >= 70) {
            grade = "C";
        } else if (avg >= 60) {
            grade = "D";
        } else if (avg >= 50) {
            grade = "E";
        } else {
            grade = "F";
        }
        dto.setTotal(total);
        dto.setAvg(avg);
        dto.setGrade(grade);
        dto.setNo(no++);
        list.add(dto);
        System.out.println(dto);
        findList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ScoreDTO>> findList(){
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<List<ScoreDTO>> del(@PathVariable("no") String no) {
        int v = Integer.parseInt(no);
        list = list.stream().filter(i->i.getNo()!=v).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // 마지막 문제 > 국어 점수가 50 이상인 것을 출력하는 코드 작성
    @GetMapping("/filter")
    public ResponseEntity<List<ScoreDTO>> filteredList() {
        List<ScoreDTO> f = list.stream().filter(i->i.getKorea()>= 50).collect(Collectors.toList());
        System.out.println("리스트 : " + f);
        return ResponseEntity.ok(f);
    }
}
