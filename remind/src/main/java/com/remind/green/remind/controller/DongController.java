package com.remind.green.remind.controller;

import com.remind.green.remind.dto.DongDTO;
import com.remind.green.remind.entity.Dongsamuso;
import com.remind.green.remind.repository.DongRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/dong")
@Slf4j
public class DongController {
    @Autowired
    DongRepository repo;

    //ans3 > toDTOs를 만드시오
    @GetMapping("/d")
    private DongDTO toDTOs (Dongsamuso d) {
        DongDTO dto = new DongDTO();
        dto.setNo(d.getNo());
        dto.setPrice(d.getPrice());
        dto.setTotal(d.getTotal());
        dto.setMath(d.getMath());
        dto.setEng(d.getEng());
        dto.setKorea(d.getKorea());
        dto.setName(d.getName());
        dto.setAvg(d.getAvg());
        return dto;
    }

    //ans4> findAll() -> dtoList로 변환
    @GetMapping("/dd")
    public ResponseEntity <List<DongDTO>> trasnferList() {
        DongDTO dto = new DongDTO();
        List<Dongsamuso> dong = repo.findAll();
        List<DongDTO> dList = new ArrayList<>();
        for (Dongsamuso l : dong) {
            dList.add(toDTOs(l));
        }
        return ResponseEntity.ok(dList);
    }
}
