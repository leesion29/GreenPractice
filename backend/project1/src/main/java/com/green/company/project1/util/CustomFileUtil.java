package com.green.company.project1.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomFileUtil {
    //롬복X
    @Value("${com.green.company.project1.upload.path}")
    private String uploadPath;

    @PostConstruct
    public void  init(){
        log.info("tomcat server initial");
        File tempFolder = new File(uploadPath);
        if(tempFolder.exists()==false)  tempFolder.mkdir();
        uploadPath = tempFolder.getAbsolutePath();
        log.info("=============");
        log.info(uploadPath);
    }
    public List<String> saveFiles(List<MultipartFile> files) throws RuntimeException {
        if(files==null || files.size()==0) return null;
        List<String> uploadNames = new ArrayList<>();
        for(MultipartFile i : files) {
            String savedName = UUID.randomUUID()+ "_"
                    + i.getOriginalFilename(); // 파일 이름 중복 방지
            Path savePath = Paths.get(uploadPath, savedName);
            try {
                Files.copy(i.getInputStream(), savePath);
                // 추가 : 파일이 이미지면, 작은 사이즈의 썸네일 처리
                String contentType = i.getContentType();
                if (contentType != null && contentType.startsWith("image")){
                    // 이미지 여부 확인
                    Path thumbnailPath = Paths.get(uploadPath, "s_" + savedName); // 이미지를 저장하면 2 개 파일 저장
                    //썸네일은 uuid + s_ + 파일명
                    Thumbnails.of(savePath.toFile()).size(200, 200).toFile((thumbnailPath.toFile()));
                    // 200x200px 사이즈
                }
                uploadNames.add(savedName);
            } catch (IOException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return uploadNames;
    }
    // 업로드 파일 보여주기
    //이미지를 데이터베이스에 저장하지 않고 파일 서버에 저장. 데이터 베이스에는 파일 서버의 경로를 저장한다.
    public ResponseEntity<Resource> getFile(String fileName) {
        Resource resource = new FileSystemResource(uploadPath + File.separator+fileName);
        if(!resource.exists()) resource = new FileSystemResource(uploadPath+File.separator+"default.jpg");
        HttpHeaders headers = new HttpHeaders();
        try {
            //probeContentType -> 컨텐츠 타입을 probe(조사)
            headers.add("Content-type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }
    public void deleteFiles(List<String> fileNames){
        if(fileNames==null || fileNames.size()==0){
            fileNames.forEach(i->{
                //썸네일이 있는 지 확인하고 삭제
                String thumbnailFileName = "_s" + i;
                Path thumbnailPath = Paths.get(uploadPath, thumbnailFileName);
                Path filePath = Paths.get(uploadPath, i);
                try {
                    Files.deleteIfExists(filePath);
                    Files.delete(thumbnailPath);
                } catch (IOException e){
                    throw  new RuntimeException(e.getMessage());
                }
            });
        }
    }
}
