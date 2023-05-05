package com.yash.blog.payloads;


import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	private Long postId;
	private String title;
	private String content;
	private String imagename;
	private Date adddate;
	private UserDTO user;
	private CategoryDto category;
	
}
