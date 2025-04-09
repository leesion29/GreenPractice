package com.remind.green.remind.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DongDTO {
    Long no;
    int price;
    float total;
    int math;
    int eng;
    int korea;
    String name;
    float avg;
}
