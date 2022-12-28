package com.springlearn.crudDemp.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.springlearn.crudDemp.entity.User;

public interface UserService {

	public User saveUser(User user);

	public List<User> findAll();

	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByEmail(String eamil);

	public User saveUserPicture(User userDetails, MultipartFile file) throws IllegalStateException, IOException;

	public byte[] downloadImageFromFileSystem(String filePath) throws IOException;
}
