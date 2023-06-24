package com.blog.payloads;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class LikesDto {

	private int id;

	private int postId;

	private int userId;

}
