package com.green.company.project1.repository;

import com.green.company.project1.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, String> {
    @EntityGraph(attributePaths = {"memberRoleList"})
    @Query("select m from Member m where m.email = :email")
    Member getWithRoles(@Param("email") String email);
}
