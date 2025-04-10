package com.remind.green.remind.controller;

import com.remind.green.remind.dto.MemberDTO;
import com.remind.green.remind.entity.Member;
import com.remind.green.remind.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    private MemberDTO entityToDTO(Member m) {
        // 여기를 채우시오.
        MemberDTO dto = new MemberDTO();
        dto.setMno(m.getMno());
        dto.setEmail(m.getEmail());
        dto.setName(m.getName());
        dto.setPassword(m.getPassword());
        dto.setAddress(m.getAddress());
        dto.setCity(m.getCity());
        return dto;
    }
    @PostMapping("/read")
    public ResponseEntity<MemberDTO> read(@RequestBody MemberDTO dto){
        Member user = memberRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        // 여기 채우세요
        System.out.println(user);
        MemberDTO yourDTO = new MemberDTO();
        if(user != null) { //react에서 이메일과 비번이 일치할 경우만 memberDTO를 반환
            yourDTO = entityToDTO(user);
            return ResponseEntity.ok(yourDTO);
        } else {
            System.out.println("일치하지 않습니다.");
        }
        return ResponseEntity.ok(null);
    }
}
