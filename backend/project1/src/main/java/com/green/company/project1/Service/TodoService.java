package com.green.company.project1.Service;

import com.green.company.project1.dto.PageRequestDTO;
import com.green.company.project1.dto.PageResponseDTO;
import com.green.company.project1.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    Long register(TodoDTO todoDTO);
    TodoDTO get(Long tno);
    void modify(TodoDTO todoDTO);
    void remove(Long tno);
    List<TodoDTO> find();
    PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);
}

