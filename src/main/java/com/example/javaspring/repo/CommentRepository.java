package com.example.javaspring.repo;

import com.example.javaspring.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>  {

}
