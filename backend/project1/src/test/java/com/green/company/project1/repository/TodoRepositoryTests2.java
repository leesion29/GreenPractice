package com.green.company.project1.repository;

import com.green.company.project1.domain.Todo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
public class TodoRepositoryTests2 {

    @Autowired
    private TodoRepository repository;

    @Test
    public void testPaging() {
        Map<String, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }

        Pageable pageable = null;
        Page<Todo> result = null;

        map.put("data", list);
        for (int i : map.get("data")) {
            // map의 data(List/value)를 사용하여 page(1,2,3, ... 10)
            pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
            result = repository.findAll(pageable);
            log.info("page" + i + "==========");
            //log.info("result 총 데이터의 갯수:" + result.getTotalElements());
            result.getContent().forEach(j -> log.info("todo: " + j));
        }

        // map.get("data");
        // 총 데이터의 마지막 페이지만 출력
        int size = 7;
        long lastPage = result.getTotalElements() / size;
        long lastPageData = result.getTotalElements() % size;
        log.info("last : " + lastPage + ", data:" + lastPageData);
        pageable = PageRequest.of((int) (lastPage + 1), (int) lastPageData, Sort.by("tno").descending());
        result = repository.findAll(pageable);
        log.info("last page ==========");
        result.getContent().forEach(j -> log.info("todo:" + j));


        //page1 ================
        //10개
        //page2 ================
        // 10개
    }

    @Test
    public void testOrdering(){
        String[] str={"tno","writer","dueDate"};
        int[] d ={1,2,3,4,11};
        Pageable pageable;
        for(String i :str)    {
            for(int k: d){
                log.info(k+"page======================================= ");
                pageable = PageRequest.of(k,10, Sort.by(i).descending());
                Page<Todo> result = repository.findAll(pageable);
                result.getContent().forEach(j -> log.info("todo: " + j));
            }
            log.info("========================="+ i +"keyword");
        }
    }
}