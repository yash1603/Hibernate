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
import com.yash.blog.payloads.UserDTO;
import com.yash.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDTO >createUser(@Valid @RequestBody UserDTO dto){
		UserDTO userDto = this.userService.createUser(dto);
		
		return new ResponseEntity<UserDTO>(userDto, HttpStatus.CREATED);
		
	}
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO >UpdateUser(@Valid @RequestBody UserDTO dto,@PathVariable Long userId){
		UserDTO userDto = this.userService.updateUser(dto, userId);
		
		return  ResponseEntity.ok(userDto);
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse>UpdateUser(@PathVariable Long userId){
		 this.userService.deleteUser(userId);
		
		 return new ResponseEntity<>(new ApiResponse("User Deleted",true),HttpStatus.OK);
		
		
	}
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUser(){
		
		return ResponseEntity.ok(this.userService.getAllUser());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId){
		
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
}
