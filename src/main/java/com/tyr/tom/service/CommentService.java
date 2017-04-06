package com.tyr.tom.service;

import com.tyr.tom.domain.Comment;

public interface CommentService {

	void addNewComment(Comment comment);
	
	void deleteComment(Comment comment);
	
	Comment getCommentById(long commentId);
}
