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
import com.yash.blog.payloads.CategoryDto;
import com.yash.blog.services.CategoryServices;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	
	@Autowired
	private CategoryServices categoryServices;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategoryDto = this.categoryServices.createCategory(categoryDto);
		
		return new  ResponseEntity<CategoryDto> (createCategoryDto,HttpStatus.CREATED);
	}
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto , @PathVariable Long categoryId ){
		CategoryDto updateCategoryDto = this.categoryServices.updateCategory(categoryDto,categoryId);
		
		return new  ResponseEntity<CategoryDto> (updateCategoryDto,HttpStatus.CREATED);
	}
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId ){
		 this.categoryServices.deletecategory(categoryId);
		
		return new  ResponseEntity<ApiResponse> ( new ApiResponse("category deleted" ,true),HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId ){
		CategoryDto CategoryDto = this.categoryServices.getCategoryById(categoryId);
		
		return new  ResponseEntity<CategoryDto> (CategoryDto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategoryDto(){
		
		return ResponseEntity.ok(this.categoryServices.getAllCategory());
	}
}
