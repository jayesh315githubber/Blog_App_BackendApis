//package com.blog.repositories;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.blog.entities.Likes;
//import com.blog.payloads.LikesDto;
//
//public interface LikesRepo extends JpaRepository<Likes, Integer> {
//
//	@Query("select p from Likes p where p.post.postId=?1 and p.user.id=?2")
//	Optional<Likes> findByPostIdAndUserId(Integer postId, Integer userId);
//
//	@Query("select p from Likes p where p.post.postId=?1")
//	List<Likes> getAllPostCount(Integer postId);
////
////	@Query("insert into Likes (post, user) select postId from Post  where postId = ?1 select id from User  where u.id = ?2")
////	Likes insertLike(Integer postId, Integer userId);
//	
//
//}
