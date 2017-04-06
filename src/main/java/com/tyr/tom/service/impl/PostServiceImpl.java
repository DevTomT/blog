package com.tyr.tom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tyr.tom.domain.Post;
import com.tyr.tom.repository.PostRepository;
import com.tyr.tom.service.PostService;

@Transactional
@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;

	@Autowired
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public void addNewPost(Post post) {
		postRepository.save(post);
	}

	@Override
	public Post getPostById(long postId) throws Exception {
		Post found = postRepository.findOne(postId);
		if (found == null) {
			throw new Exception();
		}
		return found;
	}

	@Override
	public Page<Post> getPosts(Pageable pageable) {
		return postRepository.findAll(pageable);
	}

	@Override
	public void deletePostById(long postId) {
		postRepository.delete(postId);
	}

	@Override
	public Post editPost(Post postToBeEdited) {
		return postRepository.save(postToBeEdited);
	}

	@Override
	public List<Post> getPostList() {
		List<Post> postList = new ArrayList<>();
		postRepository.findAll().forEach(postList::add);

		return postList;
	}

	@Override
	public Page<Post> getPostsByCreationDateDesc(Pageable pageable) {
		return postRepository.findAllByOrderByCreationDateDesc(pageable);
	}

}
