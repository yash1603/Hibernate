package com.yash.blog.services;

import java.util.List;

import com.yash.blog.entities.Post;
import com.yash.blog.payloads.PostDto;
import com.yash.blog.payloads.PostResponse;



public interface PostService {
	
	PostDto creatPost(PostDto dto,Long uid, Long cid);
	
	PostDto UpdatePost(PostDto dto,Long pId);
	
	void deletePost(Long pId);
	
	PostDto getPostById(Long pId);
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	PostResponse getPostByCategory(Long cId ,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	PostResponse getPostByUser(Long uId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	List<PostDto> serachPost(String keyword);
	
}
