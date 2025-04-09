package com.remind.green.remind.controller;


import com.remind.green.remind.dto.PostDTO;
import com.remind.green.remind.entity.Post;
import com.remind.green.remind.repository.CommentRepository;
import com.remind.green.remind.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin("http://localhost:3000")
@RequestMapping("/post")
@Slf4j
// 리액트에서 컨트롤러로 접근하여 리액트에서 브라우저로 테이블 형태로 출력하세요.
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    private PostDTO toDTOs(Post p) {
        PostDTO dto = new PostDTO();
        dto.setId(p.getId());
        dto.setTitle(p.getTitle());
        dto.setContent(p.getContent());
        dto.setComments(p.getComments());
        return dto;
    }

    @GetMapping("/list")
    public ResponseEntity<List<PostDTO>> listPost(){
        System.out.println("여기 잘 들어오는가?");
        List <Post> list = postRepository.findAll();
        List <PostDTO> dList = new ArrayList<>();
        for(Post p : list) {
            dList.add(toDTOs(p));
        }
        //dtoList 변환
        return ResponseEntity.ok(dList);
    }
}
