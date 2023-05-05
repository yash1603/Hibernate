package com.yash.blog.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "user_name" , nullable = false , length = 100)
	private String name;
	
	private String email;
	private String about;
	private String password;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL ,fetch =  FetchType.LAZY )
	@JsonBackReference
	private List<Post> posts = new ArrayList<>();
	

	
}
