package com.green.company.project1.repository;

import com.green.company.project1.domain.Product;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Slf4j
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository repo;

    @Test
    public void testInsert() {
        for (int i = 0; i < 10; i++) {
            Product product = Product.builder()
                    .pname("상품" + i)
                    .price(100 * i)
                    .pdesc("상품 설명" + i)
                    .build();
            // 2개의 이미지 파일 추가
            product.addImageString("IMG1.jpg");
            product.addImageString("IMG2.jpg");
            repo.save(product);
            log.info("==========================");
        }
    }

    @Commit
    @Transactional
    @Test
    public void testDelete() {
        repo.updateToDelete(2l, true);
    }

    @Test
    public void testRead2() {
        Optional<Product> res = repo.selectOne(1l);
        Product product = res.orElseThrow();
        log.info("" + product);
        log.info("imagelist: " + product.getImageList());
    }

    @Test
    public void testDelte2() {
        repo.deleteById(1l);
    }

    @Test
    public void testUpdate() {
        //repo.findAll();
        Product res = repo.selectOne(10l).get();
        res.changeName("내용 수정됨");
        res.changeDesc("10번 수정");
        res.changePrice(777);
        //첨부파일 수정
        res.clearList();
        res.addImageString(UUID.randomUUID().toString() + "_" + "새 이미지 1.jpg");
        res.addImageString(UUID.randomUUID().toString() + "_" + "새 이미지 2.jpg");
        res.addImageString(UUID.randomUUID().toString() + "_" + "새 이미지 3.jpg");
        repo.save(res);
    }

    @Test
    public void testUpdata() {
        List<Long> pnoList = repo.findAll().stream().map(i -> i.getPno()).toList();
        pnoList.forEach(i -> {
            /*
            List<Long> pnoList = new ArrayList<>();
            List<Product> productList = repo.findAll();
            for(Product i : productList){
            Long pno = i.getPno();
            pnoList.add(pno);
            }
            */
            Product res = repo.selectOne(i).get();
            res.changeName(i + (int) (Math.random() * 100) + "수정");
            res.changeDesc(i + (int) (Math.random() * 100) + "수정");
            res.changePrice((int) (Math.random() * 4000));
            res.clearList();
            for (int j = 0; j < 1 + (int) (Math.random() * 8); j++) {
                res.addImageString(UUID.randomUUID().toString() + "_" + "새로운 이미지" + (int) (Math.random() * 77) + ".jpg");
            }
            repo.save(res);
        });
    }
}
