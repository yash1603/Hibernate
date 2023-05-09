package com.yash.blog.payloads;


import com.yash.blog.entities.Post;

import lombok.Data;


@Data
public class CommentDto {

	private Long id;

	private String comment;
	
	
}
