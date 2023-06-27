package com.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.payloads.LikesDto;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.LikesService;

@Service
public class LikesServiceImpl implements LikesService {

//	@Autowired
//	private LikesRepo likesRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PostRepo postRepo;

	@Override
	public Integer getLikesCountOnPost(Integer postId) {

		Post post = this.postRepo.findById(postId).orElseThrow();

		Set<User> likedByUsers = post.getUsersWhoLiked();

		return likedByUsers.size();
	}

	@Override
	public boolean insertLikeOrUnlikeOnPost(Integer postId, Integer userId) {

//		Post post = this.postRepo.findByPostId(postId);
//		User user = this.userRepo.findById(userId).orElseThrow();
//
//		user.getLikedPosts().add(post);
//		post.getLikedByUsers().add(user);
//
//		this.userRepo.save(user);
//		this.postRepo.save(post);
//
//		return "User has Like the Post";

		Post post = this.postRepo.findByPostId(postId);
		User user = this.userRepo.findById(userId).orElseThrow();

		if (user.getLikedPosts().contains(post)) {

			user.getLikedPosts().remove(post);
			post.getUsersWhoLiked().remove(user);

			this.userRepo.save(user);
			this.postRepo.save(post);
			System.out.println(post);
			return false;

//			return "User has Like the Post";
		} else {

			user.getLikedPosts().add(post);
			post.getUsersWhoLiked().add(user);

			this.userRepo.save(user);
			this.postRepo.save(post);
			return true;
//			return "User has Unlike the Post";
		}
	}

//	@Override
//	public List<LikesDto> getLikesCountOnPost(Integer postId) {
//
////		List<Likes> postLikes = this.likesRepo.findByPostId(postId);
//		List<Likes> postLikes = this.likesRepo.getAllPostCount(postId);
//		List<LikesDto> collect = postLikes.stream().map((like) -> this.modelMapper.map(like, LikesDto.class))
//				.collect(Collectors.toList());
//		return collect;
//	}

//
//	@Override
//	public LikesDto insertLikeUnlikeOnPost(Integer postId, Integer userId) {
//
//		Optional<Likes> likepost = this.likesRepo.findByPostIdAndUserId(postId, userId);
//
////		if(!(likepost.equals(null))) {
////			return this.modelMapper.map(likepost,LikesDto.class);
////		}else {
////			return null;
////		}
//		return this.modelMapper.map(likepost, LikesDto.class);
//	}
//
//	@Override
//	public LikesDto insertLikeToPost(Integer postId, Integer userId) {
//
//		Likes likes = this.likesRepo.insertLike(postId, userId);
//
//		return this.modelMapper.map(likes, LikesDto.class);
//	}

//	@Override
//	public boolean isLikedByUser(Integer postId, Integer userId) {

//	return null
//	}

}
