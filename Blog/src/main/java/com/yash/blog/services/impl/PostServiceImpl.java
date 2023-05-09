package com.yash.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.yash.blog.entities.Category;
import com.yash.blog.entities.Post;
import com.yash.blog.entities.User;
import com.yash.blog.exceptions.ResourceNotFoundException;
import com.yash.blog.payloads.CategoryDto;
import com.yash.blog.payloads.PostDto;
import com.yash.blog.payloads.PostResponse;
import com.yash.blog.payloads.UserDTO;
import com.yash.blog.repositories.CategoryRepo;
import com.yash.blog.repositories.CommentRepo;
import com.yash.blog.repositories.PostRepo;
import com.yash.blog.repositories.UserRepo;
import com.yash.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private CommentRepo commentRepo;

	@Override
	public PostDto creatPost(PostDto dto, Long uid, Long cid) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(uid).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", uid));
		Category category = this.categoryRepo.findById(cid)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", cid));
		Post post = this.modelMapper.map(dto, Post.class);
		post.setAdddate(new Date());
		post.setImagename("default.png");
		post.setCategory(category);
		post.setUser(user);

		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto UpdatePost(PostDto dto, Long pId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(pId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", pId));
		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());
		post.setImagename(dto.getImagename());
		Post updatePost = this.postRepo.save(post);
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Long pId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(pId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", pId));
		this.postRepo.delete(post);
	}

	@Override
	public PostDto getPostById(Long pId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(pId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", pId));

		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> findAll = this.postRepo.findAll(pageable);
		List<Post> allPost = findAll.getContent();
		List<PostDto> postDtos = allPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pageNumber);
		postResponse.setPageSize(pageSize);
		postResponse.setTotalElements(findAll.getTotalElements());
		postResponse.setTotalPages(findAll.getTotalPages());
		postResponse.setLastPage(findAll.isLast());

		return postResponse;
	}

	@Override
	public PostResponse getPostByCategory(Long cId, Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Category category = this.categoryRepo.findById(cId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", cId));
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> findAll = this.postRepo.findAllByCategory(category, pageable);
		List<Post> allPost = findAll.getContent();
		List<PostDto> dtos = allPost.stream().map((list) -> this.modelMapper.map(list, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(dtos);
		postResponse.setPageNumber(pageNumber);
		postResponse.setPageSize(pageSize);
		postResponse.setTotalElements(findAll.getTotalElements());
		postResponse.setTotalPages(findAll.getTotalPages());
		postResponse.setLastPage(findAll.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getPostByUser(Long uId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		User user = this.userRepo.findById(uId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", uId));
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> findAll = this.postRepo.findAllByUser(user, pageable);
		List<PostDto> dtos = findAll.stream().map((list) -> this.modelMapper.map(list, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(dtos);
		postResponse.setPageNumber(pageNumber);
		postResponse.setPageSize(pageSize);
		postResponse.setTotalElements(findAll.getTotalElements());
		postResponse.setTotalPages(findAll.getTotalPages());
		postResponse.setLastPage(findAll.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> serachPost(String keyword) {
		// TODO Auto-generated method stub

		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

}
