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

    String address[] = {
            "수원시", "성남시", "고양시", "용인시", "부천시",
            "안산시", "안양시", "남양주시", "화성시", "평택시",
            "의정부시", "시흥시", "파주시", "김포시", "광명시"
    };

    @Test
    public void insertNewData(){
        for(int i = 0; i<15; i++) {
            Member m = new Member();
            m.setEmail("a" + i +"@naver.com");
            m.setName("홍길동");
            m.setAge((int)(i * 10 + Math.round(Math.random() * 9)));
            m.setPassword("1");
            m.setAddress(address[i] + i + "동");
            m.setCity("경기도");
            repo.save(m);
        }
    }
}
