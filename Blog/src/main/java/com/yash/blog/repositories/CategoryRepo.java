package com.yash.blog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.blog.entities.Category;
import com.yash.blog.entities.Post;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

	

}
