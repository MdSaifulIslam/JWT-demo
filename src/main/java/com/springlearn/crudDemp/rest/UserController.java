package com.springlearn.crudDemp.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springlearn.crudDemp.entity.Role;
import com.springlearn.crudDemp.entity.User;
import com.springlearn.crudDemp.entity.UserDetails;
import com.springlearn.crudDemp.entity.UsernamePassword;
import com.springlearn.crudDemp.service.RoleService;
import com.springlearn.crudDemp.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	/*
	 * User Controller ====>>> START
	 * 
	 * 
	 */

	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.findAll();
	}

	@PostMapping("/users")
	public User createNewUser(@RequestBody UserDetails userDetails) {

		final String userName = userDetails.getUserName();
		final String password = userDetails.getPassword();
		final String name = userDetails.getName();
		final String email = userDetails.getEamil();
		final int userRoleId = userDetails.getRoleId();
		final Optional<Role> userRole = roleService.findById(userRoleId);
		System.out.println(userDetails);
		System.out.println(userRole);
		
		if(userRole == null || ! userRole.isPresent()) {
			throw new RuntimeException("Role Not found");
		}

		UsernamePassword usernamePasswordId = new UsernamePassword(userName, password);

		User user = new User(name, email, usernamePasswordId, userRoleId);

		user = userService.saveUser(user);
		return new User(user.getId(), user.getName(), user.getEamil());
	}

	/*
	 * User Controller ====>>> END
	 * 
	 * 
	 */

	/*
	 * Role Controller ====>>> START
	 * 
	 * 
	 */

	@GetMapping("/roles")
	public List<Role> findAllRole() {
		return roleService.findAll();
	}

	/*
	 * Role Controller ====>>> end
	 * 
	 * 
	 */

}
