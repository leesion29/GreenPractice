package com.remind.green.remind.service;

import com.remind.green.remind.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    public List<MemberDTO> findHongAll();
    public List<MemberDTO> filterHongAge();
}
