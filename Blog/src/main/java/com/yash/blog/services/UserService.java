package com.yash.blog.services;

import java.util.List;

import com.yash.blog.payloads.UserDTO;

public interface UserService {

	
	UserDTO createUser(UserDTO user);
	
	UserDTO updateUser(UserDTO user , Long userId);
	
	UserDTO getUserById(Long userId);
	
	List<UserDTO> getAllUser();
	
	void deleteUser(Long userId);
	
	
}
