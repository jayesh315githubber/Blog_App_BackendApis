package com.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.payloads.LikesDto;


public interface LikesService {

	 Integer getLikesCountOnPost(Integer postId);

	boolean insertLikeOrUnlikeOnPost(Integer postId, Integer userId);
	
//	boolean isLikedByUser(Integer postId, Integer userId);
	
//	LikesDto insertLikeToPost(Integer postId, Integer userId);
	
	
	

}
