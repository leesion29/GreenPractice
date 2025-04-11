package com.remind.green.remind.service;

import com.remind.green.remind.dto.MemberDTO;
import com.remind.green.remind.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberserviceImpl implements MemberService {
    private final MemberRepository repo;

    @Override
    public List<MemberDTO> findHongAll() {
        return repo.findByname()
                .stream()
                .map(row -> {
                    MemberDTO dto = new MemberDTO(
                            (Long)row[0],
                            (String)row[1],
                            (int) row[2],
                            (String) row[3],
                            (String) row[4],
                            (String) row[5],
                            (String) row[6]
                    );
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDTO> filterHongAge() {
        return repo.filterByAge()
                .stream()
                .map(row -> {
                    MemberDTO dto = new MemberDTO(
                            (Long)row[0],
                            (String)row[1],
                            (int) row[2],
                            (String) row[3],
                            (String) row[4],
                            (String) row[5],
                            (String) row[6]
                    );
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
