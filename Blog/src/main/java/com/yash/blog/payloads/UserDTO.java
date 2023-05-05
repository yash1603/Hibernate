package com.yash.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
	
	private Long id;
	
	@NotEmpty
	@Size(min = 4,message = "user name must be 4 charracters")
	private String name;
	
	@Email(message = "your email is not valid")
	private String email;
	
	@NotEmpty
	private String about;
	
	@NotEmpty
	@Size(min = 3, max=10,message = "password size must be in 3-10")
	private String password;
}
