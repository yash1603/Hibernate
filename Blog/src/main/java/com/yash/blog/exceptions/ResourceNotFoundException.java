package com.yash.blog.exceptions;

import lombok.Data;

@Data

public class ResourceNotFoundException extends RuntimeException {
	

	String resourceName;
	String fildName;
	Long fildValue;
	
	public ResourceNotFoundException(String resourceName, String fildName, Long fildValue) {
		super(String.format("%s not found with %s : %s",resourceName,fildName ,fildValue));
		this.resourceName = resourceName;
		this.fildName = fildName;
		this.fildValue = fildValue;
	}
}
