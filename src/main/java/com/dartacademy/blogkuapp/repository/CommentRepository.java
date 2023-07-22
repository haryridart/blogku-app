package com.dartacademy.blogkuapp.repository;

import com.dartacademy.blogkuapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPostId(Integer postId);
    Optional<Comment> findByIdAndPostId(Integer id, Integer postId);
}
