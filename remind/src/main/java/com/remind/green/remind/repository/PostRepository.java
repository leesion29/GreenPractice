package com.remind.green.remind.repository;

import com.remind.green.remind.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
