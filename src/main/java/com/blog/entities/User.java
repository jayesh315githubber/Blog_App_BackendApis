package com.blog.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter

public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "user_name", nullable = false, length = 100)
	private String name;

	private String email;

	private String password;

	private String about;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();

//	one user can likes to many post
////	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "userswhoLike")
////	@JoinTable(name = "Post_Likes", joinColumns = @JoinColumn(name = "postId"), inverseJoinColumns = @JoinColumn(name = "userId"))
//	private Set<Post> postWhichLikes = new HashSet<>();

//	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "user")
//	private Set<Post> likes = new HashSet<>();

//	@ManyToMany(mappedBy = "userswhoLike", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private Set<Post> postWhichLikes;
	
	 @ManyToMany(cascade = { CascadeType.ALL })
	    @JoinTable(
	        name = "post_likes",
	        joinColumns = { @JoinColumn(name = "user_id") },
	        inverseJoinColumns = { @JoinColumn(name = "post_id") }
	    )
	    private Set<Post> likedPosts = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<SimpleGrantedAuthority> authories = this.roles.stream()
				.map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return authories;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
