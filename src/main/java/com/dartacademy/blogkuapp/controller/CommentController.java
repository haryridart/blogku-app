package com.dartacademy.blogkuapp.controller;

import com.dartacademy.blogkuapp.payload.ApiResponse;
import com.dartacademy.blogkuapp.payload.CommentDto;
import com.dartacademy.blogkuapp.payload.PostDto;
import com.dartacademy.blogkuapp.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(value = "CRUD REST APIs for Comment resources")
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @ApiOperation(value = "Create Comment REST API")
    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentDto> crateComment(@PathVariable("postId") Integer postId,
                                                   @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Get All Comment REST API")
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(){
        return new ResponseEntity<>(commentService.getAllComments(), HttpStatus.OK);
    }
    @ApiOperation(value = "Get Comment By Post Id REST API")
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable("postId") Integer postId){
        return new ResponseEntity<>(commentService.getCommentByPostId(postId), HttpStatus.OK);
    }
    @ApiOperation(value = "Get Comment By PostId and Comment Id REST API")
    @GetMapping("/{id}/post/{postId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("id") Integer id, @PathVariable("postId") Integer postId){
        return new ResponseEntity<>(commentService.getCommentById(id,postId),HttpStatus.OK);
    }
    @ApiOperation(value = "Update Comment REST API")
    @PutMapping("/{id}/post/{postId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("id") Integer id, @PathVariable("postId") Integer postId, @RequestBody CommentDto commentRequest){
        return new ResponseEntity<>(commentService.updateComment(id,postId,commentRequest),HttpStatus.OK);
    }
    @ApiOperation(value = "Delete Comment REST API")
    @DeleteMapping("/{id}/post/{postId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("id") Integer id, @PathVariable("postId") Integer postId){
        commentService.deleteComment(id, postId);
        return new ResponseEntity<>(new ApiResponse("Comment is successfully deleted",true),HttpStatus.OK);
    }
}
