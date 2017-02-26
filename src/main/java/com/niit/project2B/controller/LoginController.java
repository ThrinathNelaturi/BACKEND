package com.niit.project2B.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.project2B.DAO.FriendDAO;
import com.niit.project2B.DAO.UserDAO;
import com.niit.project2B.model.Friend;
import com.niit.project2B.model.User;

@RestController
public class LoginController {
	@Autowired 
	UserDAO userDAO;
	@Autowired
	FriendDAO friendDAO;

	@GetMapping("/login/{username}/{password}")
	public ResponseEntity<List<User>> login( @PathVariable("username") String username,@PathVariable("password") String password ,HttpSession session){
		User user = userDAO.authuser(username,password);
		if(user==null)
			{	return null;
	}else if(friendDAO.getfriendlist(username)==null){
		session.setAttribute("userLogged", user);
		session.setAttribute("uid", user.getId());
		session.setAttribute("username",user.getUsername());
		user.setStatus('o');
		userDAO.saveorupdate(user);
		List<User> user1=userDAO.getuser(user.getId());
		return new ResponseEntity<List<User>>(user1,HttpStatus.OK);
	}else{
		session.setAttribute("userLogged", user);
		session.setAttribute("uid", user.getId());
		session.setAttribute("username",user.getUsername());
		user.setStatus('o');
		userDAO.saveorupdate(user);
    	List<Friend> friend=friendDAO.setonline(user.getUsername());
    	for(int i=0;i<friend.size();i++){
    		Friend online=friend.get(i);
    		online.setIsonline('o');
    		friendDAO.saveorupdate(online);
    	}
		List<User> user1=userDAO.getuser(user.getId());
		return new ResponseEntity<List<User>>(user1,HttpStatus.OK);
	}
	}
	@PostMapping("/logout")
	public ResponseEntity<User> logout(HttpSession session){
		int uid =  (Integer) session.getAttribute("uid");
		User user =userDAO.oneuser(uid);
		user.setStatus('N');
		userDAO.saveorupdate(user);
		List<Friend> friend=friendDAO.setonline(user.getUsername());
		for(int i=0;i<friend.size();i++){
    		Friend online=friend.get(i);
    		online.setIsonline('f');
    		friendDAO.saveorupdate(online);
    	}
		session.invalidate();
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}