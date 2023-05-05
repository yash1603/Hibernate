package com.yash.blog.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yash.blog.confige.AppConstants;
import com.yash.blog.payloads.ApiResponse;
import com.yash.blog.payloads.CategoryDto;
import com.yash.blog.payloads.PostDto;
import com.yash.blog.payloads.PostResponse;
import com.yash.blog.services.FileService;
import com.yash.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	//save post
	@PostMapping("/user/{uid}/category/{cid}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto, @PathVariable Long uid,
			@PathVariable Long cid) {

		PostDto createPost = this.postService.creatPost(dto, uid, cid);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}
	//update post
	@PutMapping("/post/{pid}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto dto, @PathVariable Long pid) {
		PostDto updatePost = this.postService.UpdatePost(dto, pid);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}
	//get post by category
	@GetMapping("/category/{cid}/post")
	public ResponseEntity<PostResponse> getAllByCategoryId(@PathVariable Long cid,
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir) {
		PostResponse allPost = this.postService.getPostByCategory(cid,pageNumber,pageSize,sortBy,sortDir
				);
		return new ResponseEntity<>(allPost, HttpStatus.OK);
	}
	//get post by user
	@GetMapping("/user/{uid}/post")
	public ResponseEntity<PostResponse> getAllByUserId(@PathVariable Long uid,
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir) {
		PostResponse allPost = this.postService.getPostByUser(uid, pageNumber, pageSize,sortBy,sortDir);
		return new ResponseEntity<>(allPost, HttpStatus.OK);
	}
	// get all post
	@GetMapping("/post")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue =AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir) {
		PostResponse allPost = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<>(allPost, HttpStatus.OK);
	}
	//get by post id
	@GetMapping("/post/{pid}")
	public ResponseEntity<PostDto> getCategoryById(@PathVariable Long pid) {
		PostDto postDto = this.postService.getPostById(pid);

		return new ResponseEntity<>(postDto, HttpStatus.OK);
	}
	//delete
	@DeleteMapping("/post/{pid}")
	public ApiResponse deletePost(@PathVariable Long pid) {
		this.postService.deletePost(pid);
		return new ApiResponse("deleted", true);
	}
	
	//search
	@GetMapping("/post/serch/{keyword}")
	public ResponseEntity<List<PostDto>> searchByPostTitle(@PathVariable String keyword){
		List<PostDto> result = this.postService.serachPost(keyword);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	//post image uplode
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Long postId ) throws IOException{
		PostDto postDto= this.postService.getPostById(postId);
		String filename =  this.fileService.uploadImage(path, image);
		
		postDto.setImagename(filename);
		PostDto updatePost = this.postService.UpdatePost(postDto, postId);
		return new ResponseEntity<PostDto> (updatePost,HttpStatus.OK);
		
	}
}
