package com.green.company.project1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data //-> 겟터 셋터를 한꺼번에 해준다. 그러나 권장하지 않음. 불필요한 것 까지 넣어지기 때문
@Builder // 셋터를 편히 쓰기 위해서 넣은 것
// 생성자를 자동 생성해주는 어노테이션
@AllArgsConstructor // 매개변수가 있는 생성자
@NoArgsConstructor //기본 생성자
public class TodoDTO {
    private Long tno; // 주키면 자동_증가
    private String title;
    private String writer;
    private boolean complete;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
}
