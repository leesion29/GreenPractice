package com.green.company.project1.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GreenVO {
    private int price;
    private int total;
    private String name;
    float vat;
}
