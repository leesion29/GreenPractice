package com.green.company.project1.repository;

import com.green.company.project1.domain.Member;
import com.green.company.project1.domain.MemberRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Slf4j
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository repo;

    @Autowired
    private PasswordEncoder encoder;


    @Test
    public void testInsertMember(){

        for (int i = 0; i < 10 ; i++) {
            Member member = Member.builder()
                    .email("user"+i+"@aaa.com")
                    .pw(encoder.encode("1111"))
                    .nickname("USER"+i)
                    .build();
            member.addRole(MemberRole.USER);
            if(i >= 5){
                member.addRole(MemberRole.MANAGER);
            }
            if(i >=8){
                member.addRole(MemberRole.ADMIN);
            }
           repo.save(member);
        }
    }

//    @Test
//    public void TestRead() {
//        repo.getWithRoles("user9@aaa,com");
//        log.info("============== member");
//        // ans1 > member 테이블의 전체 데이터를 조회하여 MemberDTO에 저장하자
//        String[] nicknames = {"USER0", "USER1", "USER2", "USER3", "USER4", "USER5", "USER6", "USER7", "USER8", "USER9"};
//        List<MemberDTO> dtoList = new ArrayList<>();
//        log.info(repo.findAll().toString());
//        repo.findAll().forEach(i->dtoList.add(toDto(i))); // for each -> consumer 리턴 불가(소비만 한다) dtoList = 블라블라 불가
//        // ModelMapper, builder, setter 생성자
//    }
//    private MemberDTO toDto(Member i) {
//        return new MemberDTO(i.getEmail(), i.getPw(), i.getNickname(), i.isSocial());
//    }
}

