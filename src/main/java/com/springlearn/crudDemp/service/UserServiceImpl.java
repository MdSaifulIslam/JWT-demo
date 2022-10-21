package com.springlearn.crudDemp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlearn.crudDemp.dao.UserRepository;
import com.springlearn.crudDemp.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

}
