package com.remind.green.remind.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.remind.green.remind.entity.Post;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String content;
}
