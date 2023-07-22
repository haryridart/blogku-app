package com.dartacademy.blogkuapp.controller;

import com.dartacademy.blogkuapp.payload.ApiResponse;
import com.dartacademy.blogkuapp.payload.CommentDto;
import com.dartacademy.blogkuapp.payload.PostDto;
import com.dartacademy.blogkuapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentDto> crateComment(@PathVariable("postId") Integer postId,
                                                   @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(){
        return new ResponseEntity<>(commentService.getAllComments(), HttpStatus.OK);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable("postId") Integer postId){
        return new ResponseEntity<>(commentService.getCommentByPostId(postId), HttpStatus.OK);
    }
    @GetMapping("/{id}/post/{postId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("id") Integer id, @PathVariable("postId") Integer postId){
        return new ResponseEntity<>(commentService.getCommentById(id,postId),HttpStatus.OK);
    }
    @PutMapping("/{id}/post/{postId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("id") Integer id, @PathVariable("postId") Integer postId, @RequestBody CommentDto commentRequest){
        return new ResponseEntity<>(commentService.updateComment(id,postId,commentRequest),HttpStatus.OK);
    }
    @DeleteMapping("/{id}/post/{postId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("id") Integer id, @PathVariable("postId") Integer postId){
        commentService.deleteComment(id, postId);
        return new ResponseEntity<>(new ApiResponse("Comment is successfully deleted",true),HttpStatus.OK);
    }
}
