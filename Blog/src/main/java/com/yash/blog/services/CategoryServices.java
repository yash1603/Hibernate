package com.yash.blog.services;

import java.util.List;

import com.yash.blog.payloads.CategoryDto;


public interface CategoryServices {

	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto ,Long categoryId);

	CategoryDto getCategoryById(Long categoryId);

	List<CategoryDto> getAllCategory();

	void deletecategory(Long categoryId);
}
