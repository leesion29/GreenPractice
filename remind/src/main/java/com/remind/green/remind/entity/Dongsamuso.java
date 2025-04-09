package com.remind.green.remind.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

// ans 1 > Entity 만들기
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dongsamuso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long no;
    int price;
    float total;
    int math;
    int eng;
    int korea;
    String name;
    float avg;
}
