package com.blog.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.LikesDto;
import com.blog.payloads.PostDto;
import com.blog.services.CommentService;
import com.blog.services.LikesService;

@RestController
@RequestMapping("/api")
public class LikesController {

	@Autowired
	private LikesService likesService;

	@GetMapping("/post/{postId}/likes")
	public ApiResponse getAllLikesCountOnPost(@PathVariable Integer postId) {

		List<LikesDto> likesCountOnPost = this.likesService.getLikesCountOnPost(postId);
//		return new ResponseEntity<Integer>(likesCountOnPost, HttpStatus.OK);
		likesCountOnPost.forEach((p) -> System.out.println(p.toString()));
		int count = likesCountOnPost.size();
		return new ApiResponse("Totol Post Likes : "+String.valueOf(count), true);

	}
	
	@GetMapping("user/{userId}/post/{postId}/likes")
	public ApiResponse insertLikeUnlikeOnPost(@PathVariable Integer userId, @PathVariable Integer postId) {
		
		Optional<LikesDto> likechk = Optional.ofNullable(this.likesService.insertLikeUnlikeOnPost(postId, userId));
		
//		if(likechk.equals(null)) {
////			return new ApiResponse("Like",true);
//			return new ApiResponse("Unlike",false);
//		}else {
////			throw new NullPointerException("user has not like the post");
//			return new ApiResponse("Like",true);
//		}
		
		if(likechk.isPresent()) {
			return new ApiResponse("Like",true);
		}else {
			return new ApiResponse("Unlike",false);
		}
		
	}

}
