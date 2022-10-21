package com.springlearn.crudDemp.service;

import java.util.List;

import com.springlearn.crudDemp.entity.User;

public interface UserService {
	
	public User saveUser(User user) ;
	public List<User> findAll() ;
}
