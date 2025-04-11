package com.remind.green.remind.repository;

import com.remind.green.remind.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmailAndPassword(String email, String Password);

    @Query(value = """
            select mno, email, age, name, password, address, city from member
            where name = "홍길동";
            """, nativeQuery = true)
    List<Object[]> findByname();

    @Query(value = """
            select mno, email, age, name, password, address, city from member
            where age
            between 50 and 70;
            """, nativeQuery = true)
    List<Object[]> filterByAge();
}
