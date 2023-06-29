package com.blog.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.CategoryDto;
import com.blog.payloads.TagDto;
import com.blog.services.TagService;

@RestController
@RequestMapping("/api/")
public class TagController {

	@Autowired
	private TagService tagService;

//	@PostMapping
//	public ResponseEntity<String> addTags(@RequestBody Set<String> tagNames) {
//		tagService.addTags(tagNames);
//		return ResponseEntity.ok("Tags added successfully");
//	}

	@PostMapping(value = "/tag")
	public ResponseEntity<TagDto> addNewTag(@RequestBody TagDto tagDto) {
		TagDto addNewTag = this.tagService.createNewTag(tagDto);
		return new ResponseEntity<TagDto>(addNewTag, HttpStatus.CREATED);
	}

}																																																						
