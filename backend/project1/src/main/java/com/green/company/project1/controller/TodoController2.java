package com.green.company.project1.controller;
import com.green.company.project1.domain.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/todo/*")
public class TodoController2 {
    List<Todo> list = new ArrayList<>();
    static int cnt = 0;

    @GetMapping("/list")
    public ResponseEntity<List<Todo>> todo(){
        List<Todo> todoList = new ArrayList<>();
        boolean flag = false;
        for(int i = 0; i < 100; i++){
            flag = !flag;
            //todoList.add(new Todo(0, flag, "오늘은 행복한 날 " + i, "홍길동" + i));
        };
        //list.add(new Todo(cnt++, flag, "오늘은 행복한 화요일" +cnt, "홍길동"+ cnt));
        list.forEach(i-> System.out.println(i));
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }
    
    @GetMapping("/read")
    public ResponseEntity<List<Todo>> read(String tno){
        List<Todo> find = new ArrayList<>();
        System.out.println(tno); // 여기서 검색하여 반환
        //list.add(new Todo(Integer.parseInt(tno), true, "할 일", "작성자"));
// 1.       if(3== Integer.parseInt(tno)){
//            System.out.println(tno);
//        }
// 2.       list.forEach(i-> { //advanced for문과 같은 기능을 함. 람다식.
//                if(i.getTno()==Integer.parseInt(tno)) {
//            find.add(i);
//        }
//    });
// 3.
        find = list.stream().filter(i->i.getTno()==Integer.parseInt(tno)).toList();
        // 리스트에서 검색하는 기능도 있습니다.
        return new ResponseEntity<>(find, HttpStatus.OK);
    }
    @PostMapping("/add")
    public String add(@RequestBody Todo todo){
        System.out.println(todo);
        return "성공";
    }
}

