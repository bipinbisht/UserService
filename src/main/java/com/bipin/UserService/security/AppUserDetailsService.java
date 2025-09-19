package com.bipin.UserService.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bipin.UserService.model.User;
import com.bipin.UserService.repo.UserRepo;

//Spring does not understand our custom service classes
//Spring understands UserDetailsService --- which is in real world equivalent to a class holding user specific service layer methods
//UserDetailsService is a wrapper, which will wrap our actual UserService class
@Service
public class AppUserDetailsService implements UserDetailsService {

	private UserRepo userRepo;

	AppUserDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findFirstByEmail(email);

		return new AppUserDetails(user);
	}

}
