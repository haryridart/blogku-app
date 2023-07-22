package com.dartacademy.blogkuapp.service.impl;

import com.dartacademy.blogkuapp.entity.Comment;
import com.dartacademy.blogkuapp.entity.Post;
import com.dartacademy.blogkuapp.exception.ResourceNotFoundException;
import com.dartacademy.blogkuapp.payload.CommentDto;
import com.dartacademy.blogkuapp.repository.CommentRepository;
import com.dartacademy.blogkuapp.repository.PostRepository;
import com.dartacademy.blogkuapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public CommentDto createComment(Integer postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return modelMapper.map(newComment,CommentDto.class);
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getCommentByPostId(Integer postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Integer id, Integer postId) {
        Comment comment = commentRepository.findByIdAndPostId(id,postId).orElseThrow(()->new ResourceNotFoundException("Comment","id",id));
        return modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public CommentDto updateComment(Integer id, Integer postId, CommentDto commentRequest) {
        Comment comment = commentRepository.findByIdAndPostId(id,postId).orElseThrow(()->new ResourceNotFoundException("Comment","id",id));
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());
        Comment updatedComment = commentRepository.save(comment);
        return modelMapper.map(updatedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer id, Integer postId) {
        Comment comment = commentRepository.findByIdAndPostId(id,postId).orElseThrow(()->new ResourceNotFoundException("Comment","id",id));
        commentRepository.delete(comment);
    }
}
