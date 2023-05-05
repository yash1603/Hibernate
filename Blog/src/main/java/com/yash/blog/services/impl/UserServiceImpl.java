package com.yash.blog.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.blog.entities.User;
import com.yash.blog.exceptions.ResourceNotFoundException;
import com.yash.blog.payloads.UserDTO;
import com.yash.blog.repositories.UserRepo;
import com.yash.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDTO createUser(UserDTO user) {
		// TODO Auto-generated method stub
		
		User user2 = this.dtoToUser(user);
		User saveUser = this.userRepo.save(user2);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userdto, Long userId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id", userId));
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setAbout(userdto.getAbout());
		user.setPassword(userdto.getPassword());
		User updateUser =  this.userRepo.save(user);
		UserDTO dto =this.userToDto(updateUser);
		return dto;
		
	}

	@Override
	public UserDTO getUserById(Long userId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId).orElseThrow (() -> new ResourceNotFoundException("User", " Id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDTO> getAllUser() {
		// TODO Auto-generated method stub
		List<User> users = this.userRepo.findAll();
		List<UserDTO> userDTOs =  users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		
		
		return userDTOs;
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow (() -> new ResourceNotFoundException("User", " Id", userId));
		
		this.userRepo.delete(user);
	}
	private User dtoToUser(UserDTO userDto)
	{
		User user = this.modelMapper.map(userDto, User.class);
		
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;
	}
	
	private UserDTO userToDto(User user) {
		UserDTO dto = this.modelMapper.map(user, UserDTO.class);
//		dto.setId(user.getId());
//		dto.setName(user.getName());
//		dto.setEmail(user.getEmail());
//		dto.setAbout(user.getAbout());
//		dto.setPassword(user.getPassword());
		
		return dto;
	}
}
