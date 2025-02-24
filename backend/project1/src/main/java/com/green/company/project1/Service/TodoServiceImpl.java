package com.green.company.project1.Service;

import com.green.company.project1.domain.Todo;
import com.green.company.project1.dto.PageRequestDTO;
import com.green.company.project1.dto.PageResponseDTO;
import com.green.company.project1.dto.TodoDTO;
import com.green.company.project1.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional // ACID
@Log4j2
@RequiredArgsConstructor // 생성자 자동 주입
public class TodoServiceImpl implements TodoService {
    //@Autowired
    // private ModelMapper modelMapper
    private final ModelMapper modelMapper; // 자동 주입 대상 = final
    private final TodoRepository todoRepository;

    @Override
    public Long register(TodoDTO todoDTO) {
        log.info("todo service ---------------------");
        Todo todo = modelMapper.map(todoDTO, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        return savedTodo.getTno();
    }

    @Override
    public TodoDTO get(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        TodoDTO dto = modelMapper.map(todo, TodoDTO.class);
        return dto;
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        Optional<Todo> result = todoRepository.findById(todoDTO.getTno());

        Todo todo = result.orElseThrow();
        todo.changeTitle(todoDTO.getTitle());
        todo.changeDueDate(todoDTO.getDueDate());
        todo.changeComplete(todoDTO.isComplete());

        todoRepository.save(todo);
    }

    @Override
    public void remove(Long tno) {
        todoRepository.deleteById(tno);
    }

    // 문1
    @Override
    public List<TodoDTO> find() {
        List<Todo> result = todoRepository.findAll(); // 리턴 블기, 변환 필요
        List<TodoDTO> resultDtoList = new ArrayList<>();
        result.forEach(i -> {
            TodoDTO data = modelMapper.map(i, TodoDTO.class);
            resultDtoList.add(data);
        });
        return resultDtoList;
    }

    @Override
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        // 브라우저 url list?page=5&size=10에 전달되는 값을 컨트롤러에 전달하고 그 컨트롤러에서
        // pageRequestDTO에 정보가 전달된
        // pdf 38p
        Pageable pageable =
                PageRequest.of(
                        pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("tno").descending()
                );
        Page<Todo> result = todoRepository.findAll(pageable);
        List<TodoDTO> dtoList = result.getContent().stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());
        long totalCount = result.getTotalElements();
        PageResponseDTO<TodoDTO> responseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();
        return responseDTO;
    }

//    @Override
//    public void t() {
//        todoRepository.findAll().forEach(i->{
//            Todo a = todoRepository.findById(i.getTno()).get();
//            a.setWriter("홍길동" + (int)(Math.random()*100));
//            a.setTitle("제목" + (int)(Math.random()*100));
//            todoRepository.save(a);
//        });
//    }
}

