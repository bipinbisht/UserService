package com.bipin.UserService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bipin.UserService.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{
	
	public User findFirstByEmail(String email);

}
