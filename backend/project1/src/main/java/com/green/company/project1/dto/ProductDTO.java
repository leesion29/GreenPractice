package com.green.company.project1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long pno;
    private String pname;
    private int price;
    private String pdesc;
    private boolean delFlag; // 삭제가 아니라 참인 경우, 삭제를 가정함

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();
    // 하나의 상품에 여러 이미지 등록

    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();
}

