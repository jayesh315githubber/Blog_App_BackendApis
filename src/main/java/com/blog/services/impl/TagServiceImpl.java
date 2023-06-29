package com.blog.services.impl;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blog.entities.Tag;
import com.blog.entities.User;
import com.blog.payloads.CategoryDto;
import com.blog.payloads.TagDto;
import com.blog.repositories.TagRepo;
import com.blog.services.TagService;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepo tagRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TagDto createNewTag(TagDto tagDto) {

		Tag tag = this.modelMapper.map(tagDto, Tag.class);
		Tag newTag = this.tagRepo.save(tag);
		return this.modelMapper.map(newTag, TagDto.class);
	}

	@Override
	public void deleteTag(Integer tadId) {
		// TODO Auto-generated method stub
		
	}

}
