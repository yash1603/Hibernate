package com.yash.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.blog.entities.Category;
import com.yash.blog.entities.User;
import com.yash.blog.exceptions.ResourceNotFoundException;
import com.yash.blog.payloads.CategoryDto;
import com.yash.blog.payloads.UserDTO;
import com.yash.blog.repositories.CategoryRepo;
import com.yash.blog.services.CategoryServices;
@Service
public class CategoryServiceImpl implements CategoryServices {
	
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category saveCategory = this.categoryRepo.save(category);
		return this.modelMapper.map(saveCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId) {
		// TODO Auto-generated method stub
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category update =		this.categoryRepo.save(category);
		return this.modelMapper.map(update, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
		
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> categories= this.categoryRepo.findAll();
		
		List<CategoryDto> categoryDtos =  categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
	}

	@Override
	public void deletecategory(Long categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
		this.categoryRepo.delete(category);

	}
	

}


