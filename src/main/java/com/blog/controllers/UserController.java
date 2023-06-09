package com.blog.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.UserDto;
import com.blog.services.UserService;

@RestController
@Validated
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

//	 @Valid annotation to enable the validation on UserDto
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

//		 @Valid annotation to enable the validation on UserDto
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer uid) {

		UserDto updatedUser = userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}

//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {

		this.userService.deteleUser(uid);
//		return new ResponseEntity(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {

		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getAllUsers(@PathVariable Integer userId) {

		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

	@PostMapping("/{userId}/follow")
	public ResponseEntity<String> followUser(@PathVariable("userId") Integer userId, @RequestBody Integer followerId) {
		userService.followUser(userId, followerId);
		return ResponseEntity
				.ok("User with userId : " + followerId + " has follow the userId with :" + userId + " successfully.");
	}

	@PostMapping("/{userId}/unfollow")
	public ResponseEntity<String> unfollowUser(@PathVariable("userId") Integer userId,
			@RequestBody Integer followerId) {
		userService.unfollowUser(userId, followerId);
		return ResponseEntity
				.ok("User with userId : " + followerId + " has Unfollow the userId with :" + userId + " successfully.");
	}
	
	 @GetMapping("/{userId}/followers")
	    public ResponseEntity<Set<UserDto>> getFollowers(@PathVariable("userId") Integer userId) {
	        return ResponseEntity.ok(this.userService.getFollowers(userId));
	    }
	 
	 @GetMapping("/{userId}/following")
	    public ResponseEntity<Set<UserDto>> getFollowinglist(@PathVariable("userId") Integer userId) {
	        return ResponseEntity.ok(this.userService.getFollowingList(userId));
	    }
	
}
