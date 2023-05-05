package com.yash.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDto {

	
	private Long caregoryId;
	
	@NotEmpty(message = "must be not empty")
	private String categoryTitle;
	@NotEmpty(message = "must be not empty")
	private String categoryDescription;
}
