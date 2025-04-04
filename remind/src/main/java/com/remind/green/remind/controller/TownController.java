package com.remind.green.remind.controller;

import com.remind.green.remind.dto.ScoreDTO;
import com.remind.green.remind.dto.TownDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/town")
public class TownController {
    List<TownDTO> list = new ArrayList<>();
    private static int no = 0;

    @PostMapping
    public ResponseEntity<List<TownDTO>> home(@RequestBody TownDTO dto) {
        dto.setNo(no);
        list.add(dto);
        no++;
        System.out.println("등록된 데이터 : " + list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/v")
    public ResponseEntity<List<TownDTO>> list() {
        return ResponseEntity.ok(list);
    }
    @DeleteMapping("/{no}")
    public ResponseEntity<List<TownDTO>> del (@PathVariable("no") String no) {
        int n = Integer.parseInt(no);
        list = list.stream().filter(i->i.getNo()!=n).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{no}")
    public TownDTO find(@PathVariable("no") String no) {
        int v = Integer.parseInt(no);
        List<TownDTO> data = list.stream().filter(i->i.getNo()==v).collect(Collectors.toList());
        TownDTO res = data.get(0);
        return res;
    }

    @PutMapping("modify/{no}")
    public ResponseEntity<String> mod(@PathVariable("no") String no, @RequestBody TownDTO dto){
        for(TownDTO i : list) {
            if(i.getNo() == Integer.parseInt(no)){
                i.setJumin(dto.getJumin());
                i.setPhone(dto.getPhone());
                i.setAddress(dto.getAddress());
                i.setAge(dto.getAge());
                i.setFamilycnt(dto.getFamilycnt());
                i.setName(dto.getName());
                i.setDad(dto.getDad());
                i.setMom(dto.getMom());
                break;
            }
        }
        return ResponseEntity.ok("수정 완료");
    }
}

