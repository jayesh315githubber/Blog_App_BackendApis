package com.blog.services;

import java.util.List;
import com.blog.entities.Post;
import com.blog.payloads.PostDto;

public interface PostService {

//	create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

//	update 
	PostDto updatePost(PostDto postDto, Integer postId);

//	delete
	void deletePost(Integer postId);

//	get All Posts
	List<PostDto> getAllPosts();

//	get single Post
	PostDto getPostById(Integer postId);

//	get all post by category
	List<PostDto> getAllPostByCategory(Integer categoryId);

//	get all post by user
	List<PostDto> getAllPostUser(Integer userId);

//	search posts
	List<PostDto> searchPost(String keyword);

}