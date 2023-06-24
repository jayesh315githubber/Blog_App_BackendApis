package com.blog.services.impl;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Likes;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.LikesDto;
import com.blog.payloads.PostDto;
import com.blog.repositories.LikesRepo;
import com.blog.repositories.PostRepo;
import com.blog.services.LikesService;

@Service
public class LikesServiceImpl implements LikesService {

	@Autowired
	private LikesRepo likesRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepo postRepo;

	@Override
	public List<LikesDto> getLikesCountOnPost(Integer postId) {

//		List<Likes> postLikes = this.likesRepo.findByPostId(postId);
		List<Likes> postLikes = this.likesRepo.getAllPostCount(postId);
		List<LikesDto> collect = postLikes.stream().map((like) -> this.modelMapper.map(like, LikesDto.class))
				.collect(Collectors.toList());
		return collect;
	}

	@Override
	public LikesDto insertLikeUnlikeOnPost(Integer postId, Integer userId) {

		Optional<Likes> likepost = this.likesRepo.findByPostIdAndUserId(postId, userId);
		
//		if(!(likepost.equals(null))) {
//			return this.modelMapper.map(likepost,LikesDto.class);
//		}else {
//			return null;
//		}
		return  this.modelMapper.map(likepost,LikesDto.class);
	}

//	@Override
//	public boolean isLikedByUser(Integer postId, Integer userId) {
//
//		return false;
//	}

}
