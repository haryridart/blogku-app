package com.dartacademy.blogkuapp.controller;

import com.dartacademy.blogkuapp.payload.ApiResponse;
import com.dartacademy.blogkuapp.payload.PostDto;
import com.dartacademy.blogkuapp.payload.PostResponse;
import com.dartacademy.blogkuapp.service.PostService;
import com.dartacademy.blogkuapp.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(value = "CRUD REST APIs for POST resources")
@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @ApiOperation(value = "Create Post REST API")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Get All Post REST API")
    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = Constants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE, required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy",defaultValue = Constants.SORT_BY, required = false) String sortBy,
                                                    @RequestParam(value = "sortDir",defaultValue = Constants.SORT_DIR, required = false) String sortDir){
        return new ResponseEntity<>(postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }
    @ApiOperation(value = "Get Post By Id REST API")
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }
    @ApiOperation(value = "Update Post REST API")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id") Integer id){
        PostDto postResponse = postService.updatePost(postDto,id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }
    @ApiOperation(value = "Delete Post REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("id") Integer id){
        postService.deletePost(id);
        return new ResponseEntity<>(new ApiResponse("Post is successfully deleted", true), HttpStatus.OK);
    }
}
