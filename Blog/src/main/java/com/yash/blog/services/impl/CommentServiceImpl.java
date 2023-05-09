package com.yash.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.blog.entities.Comment;
import com.yash.blog.entities.Post;
import com.yash.blog.exceptions.ResourceNotFoundException;
import com.yash.blog.payloads.CategoryDto;
import com.yash.blog.payloads.CommentDto;
import com.yash.blog.payloads.PostDto;
import com.yash.blog.repositories.CommentRepo;
import com.yash.blog.repositories.PostRepo;
import com.yash.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Long postId) {
		// TODO Auto-generated method stub
		
		Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		
		Comment comment = this.modelMapper.map(commentDto,Comment.class);
		comment.setPosts(post);
		Comment save =this.commentRepo.save(comment);
		
		
		return this.modelMapper.map(save, CommentDto.class);
	}

	@Override
	public void deleteComment(Long id) {
		// TODO Auto-generated method stub
		Comment comment = this.commentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", id));
		this.commentRepo.delete(comment);
		
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Long id) {
		// TODO Auto-generated method stub
		
		Comment comment = this.commentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", id));
		
		comment.setComment(commentDto.getComment());
		Comment comment2 = this.commentRepo.save(comment);
		
		
		return this.modelMapper.map(comment2, CommentDto.class);
	}

	@Override
	public List<CommentDto> getAllComment() {
		// TODO Auto-generated method stub
		List<Comment> comment = this.commentRepo.findAll();
		List<CommentDto> cats =  comment.stream().map((cat)-> this.modelMapper.map(cat, CommentDto.class)).collect(Collectors.toList());
		
		
		return cats;
	}

	@Override
	public CommentDto getCommentById(Long id) {
		// TODO Auto-generated method stub
		Comment comment = this.commentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", id));
		
		return this.modelMapper.map(comment, CommentDto.class);
	}

}
