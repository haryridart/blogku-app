package com.dartacademy.blogkuapp.service;

import com.dartacademy.blogkuapp.payload.PostDto;
import com.dartacademy.blogkuapp.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostDto getPostById(Integer id);
    PostDto updatePost(PostDto postDto, Integer id);
    void deletePost(Integer id);
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
}
