package com.yash.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.blog.payloads.ApiResponse;
import com.yash.blog.payloads.CommentDto;
import com.yash.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	@Autowired
	private  CommentService commentService;
	
	@PostMapping("/comment/post/{PostId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto ,@PathVariable Long PostId){
		
		CommentDto commentDto2 = this.commentService.createComment(commentDto, PostId);
		
		return new ResponseEntity<CommentDto> (commentDto2,HttpStatus.CREATED);
		
	}
	@PutMapping("/comment/{Id}")
	public ResponseEntity<CommentDto> uapadeComment(@RequestBody CommentDto commentDto ,@PathVariable Long Id){
		
		CommentDto commentDto2 = this.commentService.updateComment(commentDto, Id);
		
		return new ResponseEntity<CommentDto> (commentDto2,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId){
			this.commentService.deleteComment(commentId);
			return new ResponseEntity<ApiResponse> (new ApiResponse("deleted", true),HttpStatus.OK);
	}
	
	
	@GetMapping("/comment")
	public ResponseEntity<List<CommentDto>> getAllComment(){
		return ResponseEntity.ok(this.commentService.getAllComment());
	}
	
	@GetMapping("/comment/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable Long commentId){
			CommentDto commentDto = this.commentService.getCommentById(commentId);
			return new ResponseEntity<CommentDto> (commentDto,HttpStatus.OK);
	}
	
}
