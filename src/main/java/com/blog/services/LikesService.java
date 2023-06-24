package com.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.payloads.LikesDto;


public interface LikesService {

	List<LikesDto> getLikesCountOnPost(Integer postId);

	LikesDto insertLikeUnlikeOnPost(Integer postId, Integer userId);
	
//	boolean isLikedByUser(Integer postId, Integer userId);
	
	
	
	
	

}
