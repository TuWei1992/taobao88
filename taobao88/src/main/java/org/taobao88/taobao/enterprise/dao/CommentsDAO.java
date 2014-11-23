package org.taobao88.taobao.enterprise.dao;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.Comments;
import org.taobao88.taobao.enterprise.entity.UserT;

public interface CommentsDAO {

	public Comments getCommentById(int id);
	
	public List<Comments> getCommentsByUser(UserT user);
	
	public List<Comments> getAllComments();
	
	public int addComment(Comments comment);
	
	public void deleteComment(Comments comment);
	
}
