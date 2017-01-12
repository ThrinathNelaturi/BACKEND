package com.niit.project2B.DAO;

import java.util.List;

import com.niit.project2B.model.Friend;

public interface FriendDAO {
	public boolean saveorupdate(Friend friend);
	public boolean delete(Friend friend);
	public Friend newrequest(String uid,String fid);
	public List<Friend> getfriendlist(String uid);
	public List<Friend> getrequestlist(String uid);
	public List<Friend> setonline(String uid);
	//public List<Friend> getonlinefriends(String uid);

}
