package com.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.internal.build.AllowSysOut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.blog.entities.Category;
//import com.blog.entities.Likes;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repositories.CategoryRepo;
//import com.blog.repositories.LikesRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

//	@Autowired
//	private LikesRepo likeRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "user Id", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category Id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post updatePost = this.postRepo.save(post);
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		this.postRepo.delete(post);
	}

	/*
	 * @Override public List<PostDto> getAllPosts() {
	 * 
	 * List<Post> allPosts = this.postRepo.findAll(); List<PostDto> postDtos =
	 * allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
	 * .collect(Collectors.toList()); return postDtos; }
	 */

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

		Sort sort = (sortDirection.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePosts = this.postRepo.findAll(p);

		List<Post> allPosts = pagePosts.getContent();

		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setLastPage(pagePosts.isLast());

		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category Id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> getAllPostUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "user Id", userId));
		List<Post> posts = this.postRepo.findByUser(user);

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.searchByTitle("%" + keyword + "%");
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public Integer getAllPostLikesCount(Integer postId) {

		Post post = this.postRepo.findByPostId(postId);
		Set<User> users = post.getUsersWhoLiked();
		return users.size();
	}

//	@Override
//	public String insertLikeUnlikeToPost(Integer postId, Integer userId) {
//
//		Post post = this.postRepo.findByPostId(postId);
//
//		User user = this.userRepo.findById(userId).orElseThrow();
//
//		Optional<Likes> findByPostIdAndUserId = this.likeRepo.findByPostIdAndUserId(postId, userId);
//
//		if (findByPostIdAndUserId.isPresent()) {
//			Likes like = findByPostIdAndUserId.get();
//			this.likeRepo.delete(like);
//			return "User has Unlike the post";
//		} else {
//			user.getLikedPosts().add(post);
//			post.getLikedByUsers().add(user);
//			this.postRepo.save(post);
//			this.userRepo.save(user);
//			return "User has Like the post";
//		}

//		Set<User> users = post.getLikedByUsers();
//		
//		Optional<Stream<User>> user = Optional.ofNullable(users.stream().filter((u) -> u.getId() == userId));
//		
//		User  user1 = this.userRepo.findById(userId).orElseThrow();

//		-----------------------------------------------------------------

//		if (user.isPresent()) {
//			post.getUserswhoLike().forEach((u) -> {
//				if (u.getId() == userId) {
//					System.out.println("==========="+u);
//					post.getUserswhoLike().remove(u);
//				}
//			});
//			return "Unlike the post";
////			System.out.println("User unlike the post.");
//		}else {
//			Optional<User> user1 = this.userRepo.findById(userId);
//			User user2 = user1.get();
////			System.out.println("User has like the post");
//			post.getUserswhoLike().add(user2);
//			return "Like the post";
//	}

}
