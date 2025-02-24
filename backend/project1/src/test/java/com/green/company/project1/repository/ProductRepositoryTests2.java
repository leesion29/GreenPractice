package com.green.company.project1.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

@SpringBootTest
@Slf4j
public class ProductRepositoryTests2 {

    @Autowired
    private ProductRepository repository;

    @Test
    public void testList(){
        Pageable pageable= PageRequest.of(0,10, Sort.by("pno").descending());
        Page<Object[]> result= repository.selectList(pageable);

        result.getContent().forEach(i->log.info(""+ Arrays.toString(i)));
    }

}
