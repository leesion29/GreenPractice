package com.green.company.project1.service;

import com.green.company.project1.Service.TodoService;
import com.green.company.project1.dto.PageRequestDTO;
import com.green.company.project1.dto.PageResponseDTO;
import com.green.company.project1.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Log4j2
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("서비스 테스트")
                .writer("tester")
                .dueDate(LocalDate.of(2025, 02, 12))
                .build();
        Long tno = todoService.register(todoDTO); // 사용자가 register(메서드 명)로 정함
        log.info("TNO: " + tno);
    }


    @Test
    public void testGet() {
        TodoDTO dto = todoService.get(10l);
        log.info("dto: " +dto);
    }

    // ans1) repository findAll을 호출하면 전체 데이터를 준다
    // service에서 전체 데이터를 가져오고 여기 test 코드에서 확인한다
    // 서비스 인터페이스에 함수 등록, implement에 overridding 하고
    // 그 안을 채운다

    @Test
    public void testFind() {
        List<TodoDTO> result = todoService.find();
        result.forEach(i-> log.info("dto: " + i));
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
        .page(2)
        .size(10)
        .build();

        PageResponseDTO<TodoDTO> response = todoService.list(pageRequestDTO);
        log.info(response);
    }
}
