package com.dartacademy.blogkuapp.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@ApiModel(description = "Post model information")
@Data
public class PostDto {
    @ApiModelProperty(value = "Blog post id")
    private Integer id;
    @ApiModelProperty(value = "Blog post title")
    private String title;
    @ApiModelProperty(value = "Blog post description")
    private String description;
    @ApiModelProperty(value = "Blog post content")
    private String content;
    @ApiModelProperty(value = "Blog post comments")
    private List<CommentDto> comments =new ArrayList<>();
}
