package com.remind.green.remind.repository;

import com.remind.green.remind.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository repo;

    @Test
    public void insertData(){
        //서로 다른 20개의 데이터를 추가한다.
        for(int i = 0; i<20; i++) {
            Member p = new Member();
            p.setEmail("testmail" + i + "@naver.com");
            p.setName("홍길동" + i);
            p.setPassword(Math.round(Math.random()* 100) + "@" + i);
            p.setAddress("성남시 " + i + "동");
            p.setCity("경기도 " + i);
            repo.save(p);
        }
    }

    @Test
    public void findEmailPassword(){
        Member user = repo.findByEmailAndPassword("testmail15@naver.com", "25@15");
        System.out.println("user:" +user);
    }
}
