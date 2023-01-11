package com.springlearn.crudDemp.rest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springlearn.crudDemp.dto.AuthRequest;
import com.springlearn.crudDemp.dto.AuthResponse;
import com.springlearn.crudDemp.dto.PasswordChange;
import com.springlearn.crudDemp.entity.RawData;
import com.springlearn.crudDemp.entity.Task;
import com.springlearn.crudDemp.entity.User;
import com.springlearn.crudDemp.jwt.JwtTokenUtility;
import com.springlearn.crudDemp.service.RawDataService;
import com.springlearn.crudDemp.service.TaskService;
import com.springlearn.crudDemp.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private RawDataService rawDataService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	private Authentication authentication;
	private String accessToken;

	@Autowired
	private JwtTokenUtility jwtTokenUtility;

	@Value("${app.social.scerete}")
	private String socialSecreteKey;

	@PostMapping("/auth")
	public AuthResponse login(@RequestBody AuthRequest authRequest) {

		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

			User user = (User) authentication.getPrincipal();
			System.out.println(user);

			accessToken = jwtTokenUtility.generateAccessToken(user);

			return new AuthResponse(user.getEmail(), accessToken);

		} catch (BadCredentialsException e) {
			throw new RuntimeException("Username/Password not found");
		}
	}

	@GetMapping("/users")
	public User getUser() {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userDetails = userService.findByUsername(user.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

		System.out.println(userDetails);

		userDetails.setPassword("");

		return userDetails;
	}

	@PostMapping("/users")
	public User createNewUser(@RequestBody User userDetails) {

		if (userService.findByUsername(userDetails.getUsername()).isPresent()) {
			throw new RuntimeException("Username already taken, Please choose different username");
		}
		if (userService.findByEmail(userDetails.getEmail()).isPresent()) {
			throw new RuntimeException("Email already taken, Please choose different username");
		}
		if (userDetails.getPassword() == "") {
			throw new RuntimeException("Password is null, please insert password");
		}

		userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
		userDetails.setSocial(0);

		return userService.saveUser(userDetails);
	}

	@PutMapping("/users")
	public User updateUser(@RequestBody User userDetails) {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userFromToken = userService.findByUsername(user.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

		if (userDetails.getEmail() != null)
			userFromToken.setEmail(userDetails.getEmail());
		if (userDetails.getName() != null)
			userFromToken.setName(userDetails.getName());

		userFromToken.setAddress(userDetails.getAddress());
		userFromToken.setContact(userDetails.getContact());

		return userService.saveUser(userFromToken);
	}

	@PostMapping("/users/social")
	public AuthResponse createNewSocialUser(@RequestBody User userData) throws Exception {

		Optional<User> userPresent = userService.findByUsername(userData.getEmail());
		User user = null;

		if (userPresent.isPresent()) {
			System.out.println("user already have");
			user = userPresent.get();
			if (user.getSocial() == 1 && user.getUpdated() == 1) {
				RawData rawData = rawDataService.findByUserId(user.getId())
						.orElseThrow(() -> new UsernameNotFoundException("Not Found"));

				System.out.println(userData.getUsername() + rawData.getSecret());

				return login(new AuthRequest(userData.getEmail(), rawData.getSecret()));
			}

			if (user.getSocial() == 1 && user.getUpdated() == 0) {

				return login(new AuthRequest(userData.getEmail(), socialSecreteKey));
			}
		}

		userData.setUsername(userData.getEmail());
		userData.setSocial(1);
		userData.setPassword(passwordEncoder.encode(socialSecreteKey));

		User social_user = userService.saveUser(userData);

		System.out.println(social_user);

		return login(new AuthRequest(userData.getUsername(), socialSecreteKey));
	}

	@PostMapping("/users/picture")
	public User addPictureToUser(@RequestParam("file") MultipartFile imgFile) throws Exception {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userDetails = userService.findByUsername(user.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

		return userService.saveUserPicture(userDetails, imgFile);
	}

	@GetMapping("/users/picture")
	public ResponseEntity<?> getUserPicture() throws Exception {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userDetails = userService.findByUsername(user.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

		byte[] imageData = userService.downloadImageFromFileSystem(userDetails.getImagePath());
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
	}

	@PostMapping("/users/task")
	public Task addTask(@RequestBody Task task) throws IllegalStateException, IOException {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User userDetails = userService.findByUsername(user.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

		task.setUserId(userDetails.getId());

		return taskService.saveTask(task);
	}

	@PutMapping("/users/task")
	public Task updateTask(@RequestBody Task task) throws IllegalStateException, IOException {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User userData = userService.findByUsername(user.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

		taskService.findByIdAndUserId(task.getId(), userData.getId())
				.orElseThrow(() -> new UsernameNotFoundException("Task Not Found"));
		
		task.setUserId(userData.getId());

		return taskService.saveTask(task);

		//return "Task Updated";
	}

	@DeleteMapping("/users/task/{id}")
	public String deleteTask(@PathVariable("id") int taskId) throws IllegalStateException, IOException {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User userData = userService.findByUsername(user.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

		Task task = taskService.findByIdAndUserId(taskId, userData.getId())
				.orElseThrow(() -> new UsernameNotFoundException("Task Not Found"));

		taskService.deleteTask(task);

		return "Task Deleted";
	}

	@GetMapping("/users/task")
	public List<Task> getTask() throws IOException {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userData = userService.findByUsername(user.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

		return taskService.findByUserId(userData.getId());
	}

	@PutMapping("/users/password")
	public String ChangePassword(@RequestBody PasswordChange password) {

		System.out.println(password + password.getNewPassword());

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user = userService.findByUsername(user.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

		if (user.getUpdated() == 0 && user.getSocial() == 1) {
			System.out.println("updated  0 1");
			user.setPassword(passwordEncoder.encode(password.getNewPassword()));
			user.setUpdated(1);
			userService.saveUser(user);

			RawData rawData = new RawData(user.getId(), password.getNewPassword());
			rawDataService.saveRawData(rawData);

			return "Password changed";
		}

		if (passwordEncoder.matches(password.getOldPassword(), user.getPassword())) {
			System.out.println("changed priviously");
			user.setPassword(passwordEncoder.encode(password.getNewPassword()));
			userService.saveUser(user);

			if (user.getSocial() == 1 && user.getUpdated() == 1) {
				Optional<RawData> rawData = rawDataService.findByUserId(user.getId());
				if (rawData.isPresent()) {
					RawData newPassword = rawData.get();
					newPassword.setSecret(password.getNewPassword());
					rawDataService.saveRawData(newPassword);
				}
			}

			return "Password changed";
		}

		return "Wrong password, try again";
	}

	@GetMapping("/users/password")
	public String getPassword() {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user = userService.findByUsername(user.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

		if (user.getSocial() == 1 && user.getUpdated() == 0)
			return "Social user not updated data, default password used";

		return "";
	}
}