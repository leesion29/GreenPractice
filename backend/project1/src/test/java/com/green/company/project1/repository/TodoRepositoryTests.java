package com.green.company.project1.repository;
import com.green.company.project1.domain.Todo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository repository;

    @Test
    public void test(){
        //repository.save(new Todo(0l,"홍콩","마리아",false, LocalDate.now()));
        for (int i = 0; i <= 100; i++) {
            Todo todo = Todo.builder()
                    .complete(true)
                    .dueDate(LocalDate.now())
                    .title("타이틀")
                    .writer("홍길동" + i)
                    .build();

            repository.save(todo);
//            todo.setComplete(false);
//            todo.setTitle("");
//            todo.setDueDate(todo.getDueDate());
//            todo.setWriter("");
//            repository.save(todo);
//            log.info("사랑");
        }
    }
    @Test
    public void testRead(){
        Long tno = 33l;
        Optional<Todo> result = repository.findById(tno);
        Todo todo = result.orElseThrow();
        log.info("find: " +todo);
    }
    // 문 1)
    //tno(Id)가 1, 3, 5, 11, 23인 것 찾아라
    @Test
    public void testFindvalue(){
        int[] arr={1, 2, 3, 11, 23};
        List<Todo> result = new ArrayList<>();
        for(int i : arr){
            Todo v = repository.findById((long) i).get();
            if (v.getTno()==i){
                result.add(v);
            }
        }
        for(Todo i : result){
            log.info(""+i);
        }
    }

    @Test
    public void testFindvalueJpaMethod(){
        Long[] arr={1L, 2L, 3l, 11l, 23l};

        repository.findAllById(List.of(arr));
        List<Todo> list = repository.findAllById(List.of(arr));
        list.forEach(i->log.info("find2)" + i));
    }

    @Test
    public void testModify(){
        Long tno = 33l;
        Optional<Todo> result = repository.findById(tno);
        Todo todo = result.orElseThrow();
        todo.changeTitle("수정 제목");
        todo.changeComplete(true);
        todo.changeDueDate(LocalDate.of(2025, 02, 11));
        repository.save(todo);
    }
    @Test
    public void testDelete(){
        repository.deleteById(33L);
    }
}
