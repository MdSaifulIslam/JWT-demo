package com.springlearn.crudDemp.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springlearn.crudDemp.dao.UserRepository;
import com.springlearn.crudDemp.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	File currDir = new File("");
	String path = currDir.getAbsolutePath();
	

	private final String FOLDER_PATH = path + "\\src\\main\\resources\\static\\img\\";

	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User saveUserPicture(User user, MultipartFile file) throws IOException {

		String filePath = FOLDER_PATH + file.getOriginalFilename();
		file.transferTo(new File(filePath));
		user.setImagePath(filePath);
		return userRepository.save(user);
	}

	@Override
	public byte[] downloadImageFromFileSystem(String filePath) throws IOException {
		return Files.readAllBytes(new File(filePath).toPath());
	}

	@Override
	public Optional<User> findByEmail(String eamil) {
		return userRepository.findByEmail(eamil);
	}

}
