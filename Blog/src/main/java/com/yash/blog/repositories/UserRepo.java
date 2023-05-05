package com.yash.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.blog.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

}
