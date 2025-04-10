package com.remind.green.remind.dto;

import com.remind.green.remind.entity.Comment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDTO {
    private Long id;
    private String title;
    private String content;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentDTO> comments = new ArrayList<>();
}
