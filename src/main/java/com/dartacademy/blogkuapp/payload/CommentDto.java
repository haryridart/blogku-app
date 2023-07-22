package com.dartacademy.blogkuapp.payload;

import com.dartacademy.blogkuapp.entity.Post;
import lombok.Data;

import javax.persistence.*;

@Data
public class CommentDto {
    private Integer id;
    private String name;
    private String email;
    private String body;
}
