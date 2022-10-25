package com.springlearn.crudDemp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springlearn.crudDemp.entity.User;
import com.springlearn.crudDemp.service.UserService;
import com.springlearn.crudDemp.utility.AuthRequest;
import com.springlearn.crudDemp.utility.AuthResponse;
import com.springlearn.crudDemp.utility.JwtTokenUtility;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	private Authentication authentication;
	private String accessToken;
	
	@Autowired
	private JwtTokenUtility jwtTokenUtility;

	@PostMapping("/auth")
	public AuthResponse login(@RequestBody AuthRequest authRequest) {

		try {
			
			System.out.println(authRequest.toString());
			
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			
			User user = (User)authentication.getPrincipal();
			
			accessToken = jwtTokenUtility.generateAccessToken(user);
			
			return new AuthResponse(user.getEmail(), accessToken);
			
		} catch (BadCredentialsException e) {
			throw new RuntimeException("Username/Password not found");
		}
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		 return userService.findAll();
	}

	@PostMapping("/users")
	public User createNewUser(@RequestBody User userDetails) {
		
		if(userService.findByUsername(userDetails.getUsername()).isPresent()) {
			throw new RuntimeException("Username already taken, Please choose different username");
		}

		userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
		
		return userService.saveUser(userDetails);
	}

}