package com.green.company.project1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {
    private Long sno;
    private int math;
    private int eng;
    private int korea;
    private float total;
    private float avg;
    private String grade;
}
