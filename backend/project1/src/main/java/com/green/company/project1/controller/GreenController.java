package com.green.company.project1.controller;

import com.green.company.project1.domain.GreenVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/green/*")
public class GreenController {
    @GetMapping("/list")
    public ResponseEntity<List<GreenVO>> todo(){
        List<GreenVO> greenList = new ArrayList<>();
        int sum = 0, price = 100;
        float tax = 0.0f;
        for (int i=0;i<100;i++){
        sum += price;
        tax = Math.round(price/1.05f);
        greenList.add(new GreenVO(price, sum, "시온 ", tax));
        price += 100;
        };
        return new ResponseEntity<>(greenList, HttpStatus.OK);
    }
}
