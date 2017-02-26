package com.niit.project2B.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.niit.project2B.DAO.UserDAO;
import com.niit.project2B.model.User;

@RestController
public class UserController {
	@Autowired
	private UserDAO userDAO;
	
	@PostMapping(value = "/register")
	public ResponseEntity<User> adduser(@RequestBody User user) {
		System.out.println("hello");
		user.setStatus('n');
		user.setIsonline('N');
		userDAO.saveorupdate(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}
	
	@GetMapping(value = "/user")
	public ResponseEntity<List<User>> listuser() {
		System.out.println("list of users");
		List<User> users1 = userDAO.list();
		return new ResponseEntity<List<User>>(users1, HttpStatus.OK);
	}
	
	@GetMapping(value = "/oneuser")
	public ResponseEntity<User> oneuser(HttpSession session) {
		String username = (String) session.getAttribute("username");
		System.out.println(username);
		User oneuser = userDAO.profileof(username);
		return new ResponseEntity<User>(oneuser, HttpStatus.OK);
	}

	@PostMapping("/imageUpload")
	public void ImageUpload(@RequestBody MultipartFile file, HttpSession session) throws IOException {
		String username = (String) session.getAttribute("username");  //Get Logged in Username 
		User user = userDAO.profileof(username);  //Get user object based on username 
		System.out.println(file.getContentType() + '\n' + file.getName() + '\n' + file.getSize() + '\n'+ file.getOriginalFilename());
		user.setImage(file.getBytes());
		userDAO.saveorupdate(user);
	}

	@GetMapping("/profileimage")
	public ResponseEntity<User> profileimage(HttpSession session) {
		int uid = (Integer) session.getAttribute("uid");
		User user = userDAO.oneuser(uid);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/nonfriends")
	public ResponseEntity<List<User>> nonfriends(HttpSession session) {
		int uid = (Integer) session.getAttribute("uid");
		List<User> nonfriends = userDAO.nonfriends(uid);
		return new ResponseEntity<List<User>>(nonfriends, HttpStatus.OK);
	}

}
