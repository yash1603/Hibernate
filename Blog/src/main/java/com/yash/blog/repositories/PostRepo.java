package com.yash.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.blog.entities.Category;
import com.yash.blog.entities.Post;
import com.yash.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Long> {
	
	Page<Post> findAllByUser(User user, Pageable pageable);
	
	Page<Post> findAllByCategory(Category category, Pageable pageable );
	
	List<Post> findByTitleContaining(String title);

}
