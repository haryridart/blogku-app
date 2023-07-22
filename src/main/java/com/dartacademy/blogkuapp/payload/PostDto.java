package com.dartacademy.blogkuapp.payload;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class PostDto {
    private Integer id;
    private String title;
    private String description;
    private String content;
    private List<CommentDto> comments =new ArrayList<>();
}
