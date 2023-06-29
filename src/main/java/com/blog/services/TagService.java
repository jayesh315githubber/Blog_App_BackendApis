package com.blog.services;

import com.blog.payloads.TagDto;

public interface TagService {

//	void addTags(Set<String> tagNames);

	TagDto createNewTag(TagDto tagName);
	
	void deleteTag(Integer tadId);

}
