package org.taobao88.taobao.enterprise.DAO;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.Message;
import org.taobao88.taobao.enterprise.entity.PackageT;
import org.taobao88.taobao.enterprise.entity.UserT;

public interface MessagesDAO {

	public Message findMessageById(int id);
	
	public List<Message> findSendedMessagesByUser(UserT user);
	
	public List<Message> findReceivedMessagesByUser(UserT user);
	
	public List<Message> findAllUserMessages(UserT user);
	
	public List<Message> findMessagesByPackage(PackageT packageT);
	
	public int createMessage(Message message);
	
	public void deleteMessage(Message message);
	
	public void deleteMessagesByPackage(PackageT packageT);
	
}
