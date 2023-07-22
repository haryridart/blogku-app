package com.dartacademy.blogkuapp.payload;

import lombok.Data;

@Data
public class PostDto {
    private Integer id;
    private String title;
    private String description;
    private String content;
}
