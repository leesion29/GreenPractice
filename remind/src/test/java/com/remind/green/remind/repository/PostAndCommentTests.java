package com.remind.green.remind.repository;

import com.remind.green.remind.entity.Comment;
import com.remind.green.remind.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class PostAndCommentTests {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void insertPost(){
        Post p =new Post();
        p.setTitle("홍길동제목");
        p.setContent("홍길동 내용");
        postRepository.save(p);
    }
    String [] str={"홍","길","동","입","니","다"};
    String [] contents={"내용","입니다","감사합니다"};
    private void insertForTitle(Post p){
        String resultTitle="";
        for(int i =0;i<str.length;i++){
            int randIdx = (int)(Math.random()*str.length);
            resultTitle+=str[randIdx];
        }
        p.setTitle(resultTitle);
    }
    private void insertForContent(Post p){
        String resultContent="";
        for(int i =0;i<contents.length;i++){
            int randIdx = (int)(Math.random()*contents.length);
            resultContent+=contents[randIdx];
        }
        p.setContent(resultContent);
    }
    @Test
    public void insertForTest(){
        for(int j =0;j<20;j++){
            Post p =new Post();
            insertForTitle(p);
            insertForContent(p);
            postRepository.save(p);
        }
    }
    //    @Transactional
    @Test
    public void findAllTest(){

        List<Long> idList= postRepository.findAll().stream().map(i->i.getId()).collect(Collectors.toList());
        System.out.println("list: " +idList);
        idList.stream().forEach(i->{
            for(int j =0;j<5;j++){
                Comment c =new Comment();
                c.setContent("댓글 제목"+(i+1));
                c.setPost(new Post(i,null, "댓글 내용 " +(i+1), null));
                commentRepository.save(c); // 댓글 저장
            }
        });

    }

}
