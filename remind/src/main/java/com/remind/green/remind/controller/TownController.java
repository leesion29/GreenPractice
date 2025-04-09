package com.remind.green.remind.controller;

import com.remind.green.remind.dto.ScoreDTO;
import com.remind.green.remind.dto.TownDTO;
import com.remind.green.remind.entity.Town;
import com.remind.green.remind.repository.TownRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/town")
@Slf4j
public class TownController {
    @Autowired
    TownRepository repo;

    List<TownDTO> list = new ArrayList<>();
    private static int no = 0;

//    @PostMapping
//    public ResponseEntity<List<TownDTO>> home(@RequestBody TownDTO dto) {
//        dto.setNo(no);
//        list.add(dto);
//        no++;
//        System.out.println("등록된 데이터 : " + list);
//        return ResponseEntity.ok(list);
//    }

    private TownDTO toDTO(Town t) {
        // 여기를 채우세요.
        // 1> 엔터티를 받아서 DTO로 바꾸세요.
        TownDTO dto = new TownDTO();
        dto.setNo(t.getNo());
        dto.setJumin(t.getJumin());
        dto.setPhone(t.getPhone());
        dto.setAddress(t.getAddress());
        dto.setAge(t.getAge());
        dto.setFamilycnt(t.getFamilycnt());
        dto.setName(t.getName());
        dto.setDad(t.getDad());
        dto.setMom(t.getMom());
        return dto;
    }

    @GetMapping("/v")
    public ResponseEntity<List<TownDTO>> list() {
        TownDTO dto = new TownDTO();
        log.info("전체 town {}", repo.findAll());
        List<Town> t = repo.findAll();
        List<TownDTO> dtoList = new ArrayList<>();
        for (Town i : t) {
           dtoList.add(toDTO(i));
        }
        // 2> 여기서 작업한 결과를 ok 안에 채우세요
        return ResponseEntity.ok(dtoList);
    }
    @DeleteMapping("/{no}")
    public ResponseEntity<List<TownDTO>> del (@PathVariable("no") String no) {
        int n = Integer.parseInt(no);
        list = list.stream().filter(i->i.getNo()!=n).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
//    @GetMapping("/{no}")
//    public TownDTO find(@PathVariable("no") String no) {
//        int v = Integer.parseInt(no);
//        List<TownDTO> data = list.stream().filter(i->i.getNo()==v).collect(Collectors.toList());
//        TownDTO res = data.get(0);
//        return res;
//    }
//
//    @PutMapping("modify/{no}")
//    public ResponseEntity<String> mod(@PathVariable("no") String no, @RequestBody TownDTO dto){
//        for(TownDTO i : list) {
//            if(i.getNo() == Integer.parseInt(no)){
//                i.setJumin(dto.getJumin());
//                i.setPhone(dto.getPhone());
//                i.setAddress(dto.getAddress());
//                i.setAge(dto.getAge());
//                i.setFamilycnt(dto.getFamilycnt());
//                i.setName(dto.getName());
//                i.setDad(dto.getDad());
//                i.setMom(dto.getMom());
//                break;
//            }
//        }
//        return ResponseEntity.ok("수정 완료");
//    }
}

