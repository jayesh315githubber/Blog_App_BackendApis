package com.blog.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Post;
import com.blog.entities.Tag;

public interface TagRepo extends JpaRepository<Tag, Integer> {
	
	
	Tag findByName(String tagNames);

	List<Tag> findByNameIn(Set<String> tagNames);
	

}
