package com.niit.project2B.DAO;

import java.util.List;

import com.niit.project2B.model.ForumComment;

public interface ForumCommentDAO {
	public boolean saveorupdate(ForumComment forumcomment);
	public boolean delete(ForumComment forumcomment);
	public List<ForumComment> list(int fid);

}
