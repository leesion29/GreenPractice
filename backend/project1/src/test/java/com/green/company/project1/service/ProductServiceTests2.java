package com.green.company.project1.service;

import com.green.company.project1.Service.ProductService;
import com.green.company.project1.dto.PageRequestDTO;
import com.green.company.project1.dto.PageResponseDTO;
import com.green.company.project1.dto.ProductDTO;
import com.green.company.project1.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ProductServiceTests2 {

    @Autowired
    private ProductService Service;

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
        .page(2)
        .size(10)
        .build();
        PageResponseDTO<ProductDTO> response = Service.getList(pageRequestDTO);
        log.info(response);
        response.getDtoList().forEach(i->log.info(""+i));
    }

    @Test
    public void testRegister(){
        ProductDTO dto = ProductDTO.builder()
                .pname("새 상품 서비스")
                .pdesc("신규 상품 설명. 서비스")
                .price(1000)
                .build();
        //UUID 존재해야 함
        dto.setUploadFileNames(
                List.of(UUID.randomUUID()+"_"+"test1.png"+UUID.randomUUID()+"test2.png")
        );
        Service.register(dto);
    }

    @Test
    public void testRead() {
        ProductDTO dto = Service.get(11l);
        log.info(""+dto);
        log.info("file name : " + dto.getUploadFileNames());
    }
    //table의 모든 데이터를 조회하세요. findAll을 사용하여 productService(인터페이스)의 get 추상 메서드를 사용하세요.
    @Autowired
    private ProductRepository repo;

//@Test
//public void res(){
//    List<Product> list= repo.findAll();
//
//    list.forEach(i-> {
//        long no = i.getPno();
//        ProductDTO dto = Service.get(no);
//        log.info(dto);
//    });
//}
//    @Test
//    public void readData() {
//        List<Product> list= repo.findAll();
//
//        Map<Long, String> even = new HashMap<>();
//        Map<Long, String> odd = new HashMap<>();
//        list.forEach(i-> {
//            long no = i.getPno();
//            ProductDTO dto = Service.get(no);
//            if (no%2==0){
//                even.put(no, dto.getPdesc().toString());
//            }
//            else {
//                odd.put(no, dto.getPdesc().toString());
//            }
//
//        });
//        for (Long k:even.keySet()){
//            log.info("짝수 : "+ k + "->" + even.get(k));
//        }
//        for (Long k:odd.keySet()){
//            log.info("홀수 : "+ k + "->" + odd.get(k));
//        }
//        // {홀수:[], 짝수:[]}, pno가 홀수인 것과 짝수인 것을 분리하여 map에 저장
//    }
}
