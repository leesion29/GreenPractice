package com.green.company.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "https://localhost:3000")
public class HomeController {
    @GetMapping("/")
    public String hello(){
        System.out.println("여기에 들어오는가?");
        return "love";
    }
}
