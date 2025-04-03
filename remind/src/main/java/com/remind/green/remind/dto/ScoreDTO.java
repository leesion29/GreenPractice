package com.remind.green.remind.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {
    private int no;
    private int math;
    private int eng;
    private int korea;
    private int total;
    private int avg;
    private String grade;
}
