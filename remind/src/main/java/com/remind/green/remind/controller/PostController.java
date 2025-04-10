package com.remind.green.remind.controller;


import com.remind.green.remind.dto.CommentDTO;
import com.remind.green.remind.dto.PostDTO;
import com.remind.green.remind.entity.Comment;
import com.remind.green.remind.entity.Post;
import com.remind.green.remind.repository.CommentRepository;
import com.remind.green.remind.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

//    private PostDTO toDTOs(Post p) {
//        PostDTO dto = new PostDTO();
//        dto.setId(p.getId());
//        dto.setTitle(p.getTitle());
//        dto.setContent(p.getContent());
//        dto.setComments(p.getComments());
//        return dto;
//    }
private PostDTO toDtos(Post p) {
    PostDTO t = new PostDTO();
    t.setId(p.getId());
    t.setContent(p.getContent());
    t.setTitle(p.getTitle());
    List<Comment> commentList = p.getComments();
    List<CommentDTO> commetDtoList = commentList.stream().map(i -> toCommentDto(i)).collect(Collectors.toList());
    t.setComments(commetDtoList);
    return  t;
}

    @GetMapping("/list")
    public ResponseEntity<List<PostDTO>> listPost() {
        System.out.println("여기 잘 들어오는가?");
        List<Post> list = postRepository.findAll();
//        List<PostDTO> dList = new ArrayList<>();
//        for (Post p : list) {
//            dList.add(toDTOs(p));
//        }
        List<PostDTO> dtoList=list.stream().map(i->toDtos(i)).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDTO>> listComment(@PathVariable("postId") Long postId) {
        log.info("post id comment list : " + postId);
        //문제 -> comment 리포지터리에서 findById를 호출하여 dto로 변환하여 반환하시오.
        List<Comment> list = commentRepository.findByPostId(postId);
        List<CommentDTO> DtoList = new ArrayList<>();

        for (Comment c : list) {
            DtoList.add((toDTOs(c)));
        }
        for (CommentDTO l : DtoList) {
            System.out.println("l is " + l);
        }
        return ResponseEntity.ok(DtoList);
    }

    private CommentDTO toDTOs(Comment c) {
        CommentDTO dto = new CommentDTO();
        dto.setId(c.getId());
        dto.setContent(c.getContent());
        return dto;
    }

    // 여기는 게시글 하나에 여러 개의 댓글이 들어 있어요 그래서 하나의 댓글 번호가 리액트에서
    //넘어오면 디비에서 게시글 및 댓글 정보를 가져와 리액트에 전달
    @GetMapping("/a/{postId}")
    public ResponseEntity<PostDTO> listPost(@PathVariable("postId") Long postId){
        System.out.println("post one : " +postId);
        Post p= postRepository.findById(postId).get();
        log.info("post: " +p);
        return ResponseEntity.ok(toDtos(p));
    }

    private CommentDTO toCommentDto(Comment c) {
        return new CommentDTO(c.getId(), c.getContent());
    }
}
