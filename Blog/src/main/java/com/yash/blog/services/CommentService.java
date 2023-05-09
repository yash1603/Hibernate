package com.yash.blog.services;


import java.util.List;

import com.yash.blog.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto,Long postId);
	
	CommentDto updateComment(CommentDto commentDto,Long id);
	
	List<CommentDto> getAllComment();
	
	CommentDto getCommentById(Long id);
	
	
	void deleteComment(Long id);
}
