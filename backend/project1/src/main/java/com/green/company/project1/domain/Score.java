package com.green.company.project1.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tbl_sc")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    private int math;
    private int eng;
    private int korea;
    private float total;
    private float avg;
    private String grade;

    // 안 바뀌던 원인! 매개변수 수정
    public void changeMath(int math){this.math=math;}
    public void changeEng(int eng){this.eng=eng;}
    public void changeKorea(int korea){this.korea=korea;}
}
