package com.niit.project2B.DAO;

import com.niit.project2B.model.User;
import java.util.List;


public interface UserDAO {
	public List<User> list();
	public boolean saveorupdate(User user);
	public boolean delete(User user);
	public List<User> getuser(int id);
	public User oneuser(int id);
	public User authuser(String username, String password);
	public User profileof(String username);
	public List<User> nonfriends(int id);

}
