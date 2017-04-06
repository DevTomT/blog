package com.tyr.tom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyr.tom.domain.Comment;
import com.tyr.tom.repository.CommentRepository;
import com.tyr.tom.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentReposiory;

	@Autowired
	public CommentServiceImpl(CommentRepository commentReposiory) {
		this.commentReposiory = commentReposiory;
	}

	@Override
	public void addNewComment(Comment comment) {
		commentReposiory.save(comment);
	}

	@Override
	public void deleteComment(Comment comment) {
		commentReposiory.delete(comment);
	}

	@Override
	public Comment getCommentById(long commentId) {
		return commentReposiory.findOne(commentId);
	}

}
