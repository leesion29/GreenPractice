package com.green.company.project1.controller;

import com.green.company.project1.Service.TodoService;
import com.green.company.project1.dto.PageRequestDTO;
import com.green.company.project1.dto.PageResponseDTO;
import com.green.company.project1.dto.TodoDTO;
import com.green.company.project1.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService service;

    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable(name = "tno") Long tno) {
        return service.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);
        return  service.list(pageRequestDTO);
    }

    // pdf p.48
    @PostMapping("/")
    public Map<String, Long> register(@RequestBody TodoDTO todoDTO) {
        log.info("TodoDTO : " + todoDTO);
        Long tno = service.register(todoDTO);
        return Map.of("TNO", tno);
    }

    // 수정
    @PutMapping("/{tno}")
    public Map<String, String> modify(
            @PathVariable(name="tno") Long tno,
            @RequestBody TodoDTO todoDTO) {
        todoDTO.setTno(tno);
        log.info("controller 수정: " + tno +  "dto:" + todoDTO);
        service.modify(todoDTO);
        return Map.of("RESULT", "SUCCESS");
    }

    // 삭제
    @DeleteMapping("/{tno}")
    public Map<String, String> remove(@PathVariable(name="tno") Long tno){
        log.info("Remove: " + tno);
        service.remove(tno);
        return Map.of("RESULT", "SUCCESS");
    }

    // 문제 >  GET http://localhost:8080/api/todo/v를 입력하면 전체 데이터를 삭제하도록 하시오.
    @GetMapping("/v")
    public String deleteAll() {
        log.info("del test");
        List<TodoDTO> list = service.find();
        log.info("list" + list);

        list.forEach(i->{
            service.remove(i.getTno());
        });
        return "전체 삭제 성공";
    }
    // 문제2 > 데이터 200개를 추가하세요

    @Autowired
    private TodoRepository repository;

    @GetMapping("/dummy")
    public String addData() {
        for (int i = 0; i <= 200; i++) {
            service.register(new TodoDTO(null,
                    "내일은 금요일"+ (int)(Math.random()*100),
                    "금요일 작성자" + (int)(Math.random()*100),
                    true, LocalDate.now()));
//            Todo todo = Todo.builder()
//                    .complete(true)
//                    .dueDate(LocalDate.now())
//                    .title("오늘은 목요일!")
//                    .writer("목요일 작성자" + i)
//                    .build();
//            repository.save(todo);
        }
        return "데이터 추가 성공";
    }
}

//REST API
// DB와 1:1 매칭이 되도록 함