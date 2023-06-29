package com.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.blog.entities.Tag;
import com.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostDto {

//	note - category_id and user_id can be get in the url as well as in the dto
	
	private Integer postId;
	
	@NotBlank
	@Size(min = 4 , message ="min size of post title must be 4")
	private String title;
	
	@NotBlank
	@Size(min = 10, message = "Min size of category title is 10")
	private String content;
	
	@NotBlank
	private  String 	imageName ; 
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<UserDto> userswhoLike = new HashSet<>();
	
	private Set<CommentDto> comments=new HashSet<>();
	
	 private Set<TagDto> tags = new HashSet<>();
	 
	

}
