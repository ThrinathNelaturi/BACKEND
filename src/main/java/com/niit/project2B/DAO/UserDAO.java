package com.niit.project2B.DAO;

import com.niit.project2B.model.User;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
	public List<User> list();
	public boolean saveorupdate(User user);
	public boolean delete(User user);
	public User get(int id);
	/*oneuser,authuser,profilof,nonfriend*/
	public User oneuser(int id);

	public User authuser(String username, String password);

	public User profileof(String username);

	public List<User> nonfriends(int id);

}
