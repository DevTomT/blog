package com.tyr.tom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyr.tom.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
