package com.dartacademy.blogkuapp.payload;

import com.dartacademy.blogkuapp.entity.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
@ApiModel(description = "Comment model information")
@Data
public class CommentDto {
    @ApiModelProperty(value = "Comment id")
    private Integer id;
    @ApiModelProperty(value = "Comment name")
    private String name;
    @ApiModelProperty(value = "Comment email")
    private String email;
    @ApiModelProperty(value = "Comment body content")
    private String body;
}
