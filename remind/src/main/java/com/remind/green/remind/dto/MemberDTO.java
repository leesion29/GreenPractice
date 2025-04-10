package com.remind.green.remind.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberDTO {
    private Long mno;
    private String email;
    private String name;
    private String password;
    private String address;
    private String city;
}
