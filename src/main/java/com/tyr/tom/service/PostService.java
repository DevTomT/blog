package com.tyr.tom.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tyr.tom.domain.Post;

public interface PostService {

	// create new post
	void addNewPost(Post post);

	// show post by id
	Post getPostById(long postId) throws Exception;

	// show posts (paging)
	Page<Post> getPosts(Pageable pageable);

	// show all posts
	List<Post> getPostList();

	// show posts, order by creationDate(paging)
	Page<Post> getPostsByCreationDateDesc(Pageable pageable);

	// delete post by id
	void deletePostById(long postId);

	// edit post
	Post editPost(Post postToBeEdited);

}
