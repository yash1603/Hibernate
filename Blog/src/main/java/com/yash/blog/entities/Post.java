package com.yash.blog.entities;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yash.blog.payloads.CommentDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Table(name = "post")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	
	private String title;
	private String content;
	private String imagename;
	private Date adddate;
	
	@ManyToOne
	@JsonManagedReference
	private Category category;
	
	@ManyToOne
	@JsonManagedReference
	private User user;
	
	@OneToMany(mappedBy = "posts",cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();
}
