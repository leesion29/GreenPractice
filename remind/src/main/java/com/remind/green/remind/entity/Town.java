package com.remind.green.remind.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int no;
    private String jumin;
    private String phone;
    private String address;
    private int age;
    private int familycnt;
    private String name;
    private String dad;
    private String mom;
}
