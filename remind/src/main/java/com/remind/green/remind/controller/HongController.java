package com.remind.green.remind.controller;

import com.remind.green.remind.dto.MemberDTO;
import com.remind.green.remind.entity.Member;
import com.remind.green.remind.repository.MemberRepository;
import com.remind.green.remind.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/hong")
public class HongController {

    private final MemberService memberService;

    public HongController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/find")
    public ResponseEntity<List<MemberDTO>> read() {
        List<MemberDTO> memberList = memberService.findHongAll();
        return ResponseEntity.ok(memberList);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<MemberDTO>> filter() {
        List<MemberDTO> memberList = memberService.filterHongAge();
        return ResponseEntity.ok(memberList);
    }

}
