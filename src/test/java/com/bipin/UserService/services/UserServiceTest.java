//package com.bipin.UserService.services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.bipin.UserService.config.BcryptEncoder;
//import com.bipin.UserService.dto.UserLoginDto;
//import com.bipin.UserService.exception.PasswordNotFoundException;
//import com.bipin.UserService.model.User;
//import com.bipin.UserService.repo.UserRepo;
//
//@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//
//	@Mock
//	private UserRepo userRepo;
//
//	@Mock
//	private BcryptEncoder bcryptEncoder;
//
//	@InjectMocks
//	private UserService userService;
//
//	@Test
//	void save_shouldEncodePasswordAndSaveUser() {
//		// arrange
//		User user = new User();
//		user.setName("Bipin");
//		user.setPassword("plain123");
//
//		when(bcryptEncoder.encode("plain123")).thenReturn("encoded123");
//		when(userRepo.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
//
//		// act
//		User savedUser = userService.save(user);
//
//		// assert
//		assertEquals("encoded123", savedUser.getPassword());
//		assertEquals("Bipin", savedUser.getName());
//		verify(bcryptEncoder).encode("plain123");
//		verify(userRepo).save(user);
//	}
//
//	@Test
//	void login_Success() {
//		// Arrange
//		UserLoginDto dto = new UserLoginDto();
//		dto.setEmail("bipinbisht804@gmail.com");
//		dto.setPassword("123");
//
//		User user = new User();
//		user.setEmail("bipinbisht804@gmail.com");
//		user.setPassword("encodedPassword");
//
//		when(userRepo.findByEmail("bipinbisht804@gmail.com")).thenReturn(user);
//		when(bcryptEncoder.matches("123", "encodedPassword")).thenReturn(true);
//
//		// Act
//		User loggedInUser = userService.login(dto);
//
//		// Assert
//		assertNotNull(loggedInUser);
//		assertEquals("bipinbisht804@gmail.com", loggedInUser.getEmail());
//		verify(userRepo).findByEmail("bipinbisht804@gmail.com");
//		verify(bcryptEncoder).matches("123", "encodedPassword");
//	}
//
//	@Test
//	void login_WrongPassword_ThrowsException() {
//		// Arrange
//		UserLoginDto dto = new UserLoginDto();
//		dto.setEmail("bipinbisht804@gmail.com");
//		dto.setPassword("wrongpass");
//
//		User user = new User();
//		user.setEmail("bipinbisht804@gmail.com");
//		user.setPassword("encodedPassword");
//
//		when(userRepo.findByEmail("bipinbisht804@gmail.com")).thenReturn(user);
//		when(bcryptEncoder.matches("wrongpass", "encodedPassword")).thenReturn(false);
//
//		// Act + Assert
//		assertThrows(PasswordNotFoundException.class, () -> userService.login(dto));
//
//		verify(userRepo).findByEmail("bipinbisht804@gmail.com");
//		verify(bcryptEncoder).matches("wrongpass", "encodedPassword");
//	}
//
//}
