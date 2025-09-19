package com.bipin.UserService.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bipin.UserService.model.User;

//Spring does not understand our custom entity classes
//Spring understands UserDetails --- which is in real world equivalent to a class holding user information
//UserDetails is a wrapper, which will wrap our actual User model class
public class AppUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;

	private final User user;

	public AppUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

//		return user.getRoles().stream().map(role -> (GrantedAuthority) () -> "ROLE_" + role.getName()).toList();
		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).toList();

	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
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
