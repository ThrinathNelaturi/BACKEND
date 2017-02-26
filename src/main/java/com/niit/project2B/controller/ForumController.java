package com.niit.project2B.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.project2B.DAO.ForumDAO;
import com.niit.project2B.model.Forum;

@RestController
public class ForumController {

	@Autowired
	ForumDAO forumDAO;

	@PostMapping("/createforum")
	public ResponseEntity<Forum> createforum(@RequestBody Forum forum,HttpSession session){
		String uid=(String) session.getAttribute("username");
		forum.setStatus("n");
		forum.setDoc(new Date());
		forum.setUserid(uid);
		forumDAO.saveOrUpdate(forum);
		return new ResponseEntity<Forum>(forum ,HttpStatus.OK);
	}
	
	@GetMapping(value="/forum")
	public ResponseEntity<List<Forum>> listforum(){
		System.out.println("list of accepted forums");
		List<Forum> forum =forumDAO.userlist();
		return new ResponseEntity<List<Forum>>(forum,HttpStatus.OK);
	}
	@DeleteMapping(value="/deleteforum/{forumid}")
	public ResponseEntity<Forum> deleteforum(Forum forum,@PathVariable("formid") int forumid){
		Forum forum1=forumDAO.getforum(forumid);
		forumDAO.delete(forum1);
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	}
	@GetMapping(value="/individualforum/{id}")
	public ResponseEntity<Forum> individualforum(@PathVariable("id") int id){
		Forum forum=forumDAO.getforum(id);
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	}
	@GetMapping(value="/adminforum")
	public ResponseEntity<List<Forum>> adminforum(){
		System.out.println("list of all forums");
		List<Forum> forum =forumDAO.list();
		return new ResponseEntity<List<Forum>>(forum,HttpStatus.OK);
	}
	@PostMapping(value="/acceptforum/{id}")
	public ResponseEntity<Forum> accept(@PathVariable("id") int id){
		Forum forum=forumDAO.getforum(id);
		forum.setStatus("a");
		forumDAO.saveOrUpdate(forum);
		return new ResponseEntity<Forum>(HttpStatus.OK);	
	}
	@PostMapping(value="/rejectforum/{id}")
	public ResponseEntity<Forum> reject(@PathVariable("id") int id){
		Forum forum=forumDAO.getforum(id);
		forum.setStatus("r");
		forumDAO.saveOrUpdate(forum);
		return new ResponseEntity<Forum>(HttpStatus.OK);	
	}
}
