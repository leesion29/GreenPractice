package com.green.company.project1.Service;

import com.green.company.project1.dto.PageRequestDTO;
import com.green.company.project1.dto.PageResponseDTO;
import com.green.company.project1.dto.ProductDTO;
import jakarta.transaction.Transactional;

@Transactional
public interface ProductService {
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO dto);
    Long register(ProductDTO dto);
    ProductDTO get(Long pno);
    public void modify(ProductDTO dto);
    void remove(Long pno);
}
