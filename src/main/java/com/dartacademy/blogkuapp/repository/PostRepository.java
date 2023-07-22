package com.dartacademy.blogkuapp.repository;

import com.dartacademy.blogkuapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
