package com.dartacademy.blogkuapp.service;

import com.dartacademy.blogkuapp.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Integer postId, CommentDto commentDto);
    List<CommentDto> getAllComments();
    List<CommentDto> getCommentByPostId(Integer postId);
    CommentDto getCommentById(Integer id, Integer postId);
    CommentDto updateComment(Integer id, Integer postId, CommentDto commentRequest);
    void deleteComment(Integer id, Integer postId);
}
