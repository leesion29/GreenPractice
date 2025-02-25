package com.green.company.project1.controller;

import com.green.company.project1.Service.ProductService;
import com.green.company.project1.dto.PageRequestDTO;
import com.green.company.project1.dto.PageResponseDTO;
import com.green.company.project1.dto.ProductDTO;
import com.green.company.project1.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/products")
public class ProductController {
    private final CustomFileUtil fileUtil;
    private final ProductService service;

    @PostMapping("/")
    public Map<String, Long> register(ProductDTO dto) {
        log.info("Controller Register : " + dto);
        List<MultipartFile> fileList = dto.getFiles();
        List<String> uploadFileNames = fileUtil.saveFiles(fileList);
        dto.setUploadFileNames(uploadFileNames);
        log.info("uploadFileNames : " + uploadFileNames);
        Long pno = service.register(dto);
        return  Map.of("result", pno);
    }
    // 파일 보여주기(브라우저)
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')") // 임시 권한 설정
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGer(@PathVariable String fileName) {
        log.info("파일 명 조회 : " + fileName);
        return fileUtil.getFile(fileName);
    }

    @GetMapping("/list")
    public PageResponseDTO<ProductDTO> list(PageRequestDTO dto) {
        log.info("Product Controller List : " + dto);
        return service.getList(dto);
    }

    @PutMapping("/{pno}")
    public Map<String, String> modify(@PathVariable(name="pno")Long pno, ProductDTO dto) {
        dto.setPno(pno);
        ProductDTO oldProductDTO = service.get(pno);
        //기존의 파일들 (데이터베이스에 존재하는 파일들 - 수정 과정에서 삭제되었을 수 있음)
        List<String> oldFileNames = oldProductDTO.getUploadFileNames();
        //새로 업로드 해야 하는 파일들
        List<MultipartFile> files = dto.getFiles();
        //새로 업로드되어서 만들어진 파일 이름들
        List<String> currentUploadFileNames = fileUtil.saveFiles(files);
        //화면에서 변화 없이 계속 유지된 파일들
        List<String> uploadedFileNames = dto.getUploadFileNames();
        //유지되는  파일들 + 새로 업로드 된 파일 이름들이 저장해야 하는 파일 목록이 됨
        if(currentUploadFileNames !=null && currentUploadFileNames.size()>0) uploadedFileNames.addAll(currentUploadFileNames);
        // 수정 작업
        service.modify(dto);
        if(oldFileNames != null && oldFileNames.size() > 0){
            //지워야 하는 파일 목록 찾기
            //예전 파일들 중에서 지워져야 하는 파일이름들
            List<String> removeFiles =  oldFileNames
                    .stream()
                    .filter(fileName -> uploadedFileNames.indexOf(fileName) == -1).collect(Collectors.toList());
            //실제 파일 삭제
            fileUtil.deleteFiles(removeFiles);
        }
        return Map.of("result", "성공");
    }
    @GetMapping("/{pno}")
    public ProductDTO read(@PathVariable(name = "pno") Long pno){
        log.info("컨트롤러 데이터 한 개를 조회했습니다 -> " + pno);
        return service.get(pno);
    }

    @DeleteMapping("/{pno}")
    public Map<String, String> remove(@PathVariable(name = "pno") Long pno) {
        List<String> oldFileNames = service.get(pno).getUploadFileNames();
        service.remove(pno);
        fileUtil.deleteFiles(oldFileNames);
        return Map.of("result", "성공");
    }
}
