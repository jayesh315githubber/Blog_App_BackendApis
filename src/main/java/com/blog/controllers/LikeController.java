package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.services.LikesService;

@RestController
@RequestMapping("/api/")
public class LikeController {

	@Autowired
	private LikesService likeService;

	@PostMapping("/user/{userId}/post/{postId}/like")
	public ApiResponse likePost(@PathVariable("userId") Integer userId, @PathVariable("postId") Integer postId) {

		boolean status = this.likeService.insertLikeOrUnlikeOnPost(postId, userId);
		System.out.println(status);
		if (status) {
			return new ApiResponse("UserId " + userId + " has likes to postId :" + postId, true);
		} else {
			return new ApiResponse("UserId " + userId + " has Unlikes to postId :" + postId, true);
		}
	}

}
