package com.blog.payloads;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TagDto {
	
	private Integer id;
	
	@NotBlank
	private String name;
	
	

}
