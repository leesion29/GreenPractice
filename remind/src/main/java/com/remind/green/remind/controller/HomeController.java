package com.remind.green.remind.controller;

import com.remind.green.remind.dto.IdAndName;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "사랑";
    }

    @GetMapping("/{id}")
    public String home(@PathVariable("id") String id){
        System.out.println(id);
        return "사랑";
    }

    @GetMapping("/print")
    public String home(@RequestParam int id, @RequestParam String name){
        System.out.println("id =" + id + " name = " + name);
        return "사랑";
    }

    @PostMapping("")
    public ResponseEntity<IdAndName> postMAP(@RequestBody IdAndName vv) {
        vv.setPrice(68000);
        System.out.println(vv.getId());
        System.out.println(vv.getName());
        System.out.println(vv.getPrice());
        // 완성을 합니다(2개는 frontend에서 받고 price는 여기에서 데이터를 채운 후 반환한다)
        return ResponseEntity.ok(vv);
    }
}
