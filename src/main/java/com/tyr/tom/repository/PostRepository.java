package com.tyr.tom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tyr.tom.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	Page<Post> findAllByOrderByCreationDateDesc(Pageable pageable);
	
}