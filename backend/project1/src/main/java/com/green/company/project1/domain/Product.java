package com.green.company.project1.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_product")
@Getter
@ToString(exclude = "imageList")//imageList 를 제외(exclude)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String pname;
    private int price;
    private String pdesc;
    private boolean delFlag;// 삭제가아니라 delFlag true로 하면 삭제했다고 가정
    public void changeDel(boolean delFlag) {
        this.delFlag=delFlag;
    }
    @ElementCollection
    @Builder.Default
    private List<ProductImage> imageList=new ArrayList<>();
    public void changePrice(int price){
        this.price=price;
    }
    public void changeDesc(String  desc){
        this.pdesc=desc;
    }
    public void changeName(String name){
        this.pname=name;
    }
    public void addImage(ProductImage image){
        image.setOrd(this.imageList.size());
        imageList.add(image);
    }
    public void addImageString(String fileName){
        ProductImage productImage= ProductImage.builder()
                .fileName(fileName)
                .build();
        addImage(productImage);
    }
    //List에서 모든 데이터 제거
    public void clearList(){
        this.imageList.clear();
    }
    //tbl_product는 product_image_list 테이블을 자식으로 가진다
    //product  의 primary key(기본키) 를 product_image_list 에서  foreign key(외래)로 참조함
    //하나의 제품에는 여러개의 image가 있을 것이고
    // pno는  primary key이고 product_image_list 에서 product_pno(pno) 로 참조하고 있음


}
