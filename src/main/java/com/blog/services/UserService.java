package com.blog.services;

import java.util.List;
import java.util.Set;

import com.blog.payloads.UserDto;

public interface UserService {

	UserDto registerNewUser(UserDto user);

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deteleUser(Integer userId);

	void followUser(Integer userId, Integer followerId);

	void unfollowUser(Integer userId, Integer followerId);

	Set<UserDto> getFollowers(Integer userId);
	
	Set<UserDto> getFollowingList(Integer userId);
	

}
