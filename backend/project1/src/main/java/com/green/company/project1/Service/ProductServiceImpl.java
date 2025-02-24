package com.green.company.project1.Service;
import com.green.company.project1.domain.Product;
import com.green.company.project1.domain.ProductImage;
import com.green.company.project1.dto.PageRequestDTO;
import com.green.company.project1.dto.PageResponseDTO;
import com.green.company.project1.dto.ProductDTO;
import com.green.company.project1.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo;
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO dto) { // page, size 받아서
        // JPA의 Repository의 쿼리를 적용하여
        //해당 페이지의 내용을 엔터티로 반환 해준다.

        log.info("getList++++++++++++++++test");
        Pageable pageable = PageRequest.of(
                dto.getPage()-1,
                dto.getSize(),
                Sort.by("pno").descending()
        ); // 현재 페이지, 페이지 당 데이터의 개수, 정렬 기준
        //selectList는 @Query의 product와 image를 결합하여(join) 데이터를 달라고 요청
        Page<Object[]> res = repo.selectList(pageable);
        // 페이지 형태로 여러 개의 데이터(iterable)를 map을 돌리면서 dto로 변환(ProductDTOList)
        List<ProductDTO> dtoList = res.get().map(i->{
            Product product = (Product) i[0];
            ProductImage productImage = (ProductImage) i[1];
            ProductDTO productDTO = ProductDTO.builder()
                    .pno(product.getPno())
                    .pname(product.getPname())
                    .pdesc(product.getPdesc())
                    .price(product.getPrice())
                    .build();
            String imageStr = productImage.getFileName();
            productDTO.setUploadFileNames(List.of(imageStr));
            return productDTO;
        }).collect(Collectors.toList());
        long totalCount = res.getTotalElements();
        // PageResponseDTO에 실어보낸다(productDtoList, 총 데이터의 갯수, 현재 페이지, 페이지 당 개수를 React로 반환한다.)
        return PageResponseDTO.<ProductDTO>withAll()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .pageRequestDTO(dto)
                .build();
    }

    @Override
    public Long register(ProductDTO dto) {
        Product product = dtoToEntity(dto);
        Product result = repo.save(product);
        return result.getPno();
    }

    @Override
    public ProductDTO get(Long pno) {
        log.info("service get pno: " + pno);
        Optional<Product> res = repo.selectOne(pno);
        Product product = res.orElseThrow();
        ProductDTO dto = entity2DTO(product);
        return dto;
    }

    @Override
    public void modify(ProductDTO dto) {
        Optional<Product> res = repo.findById(dto.getPno());
        Product pr = res.orElseThrow();
        pr.changeName(dto.getPname());
        pr.changeDesc(dto.getPdesc());
        pr.changePrice(dto.getPrice());
        pr.clearList();
        List<String> uploadFileNames = dto.getUploadFileNames();
        if(uploadFileNames!=null || uploadFileNames.size()>0)
            uploadFileNames.stream().forEach(i->pr.addImageString(i));
        repo.save(pr);
    }

    @Override
    public void remove(Long pno) {
        repo.updateToDelete(pno, true);
    }

    private ProductDTO entity2DTO(Product pr){
        ProductDTO dto = ProductDTO.builder()
                .pno(pr.getPno())
                .pname(pr.getPname())
                .pdesc(pr.getPdesc())
                .price(pr.getPrice())
                .build();
        List<ProductImage> imgList = pr.getImageList();
        if(imgList==null | imgList.size()==0) return dto;
        List<String> fileNameList = imgList.stream().map(i->i.getFileName()).toList();
        dto.setUploadFileNames(fileNameList);
        return dto;
    }

    private Product dtoToEntity(ProductDTO dto) {
        Product product = Product.builder()
                .pno(dto.getPno())
                .pname(dto.getPname())
                .pdesc(dto.getPdesc())
                .price(dto.getPrice())
                .build();
        // 업로드 처리가 끝난 파일들의 이름 리스트
        List<String> uploadFileNames = dto.getUploadFileNames();
        if(uploadFileNames==null) return product;
        uploadFileNames.stream().forEach(i->product.addImageString(i));
        return product;
    }
}
