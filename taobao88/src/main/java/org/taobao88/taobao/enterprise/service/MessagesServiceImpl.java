package org.taobao88.taobao.enterprise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taobao88.taobao.enterprise.DAO.MessagesDAO;
import org.taobao88.taobao.enterprise.entity.Message;
import org.taobao88.taobao.enterprise.entity.PackageT;
import org.taobao88.taobao.enterprise.entity.UserT;

@Repository("messagesService")
public class MessagesServiceImpl implements MessagesService {

	@Autowired private MessagesDAO messagesDAO;
	
	@Override
	public Message findMessageById(int id) {
		return messagesDAO.findMessageById(id);
	}

	@Override
	public List<Message> findSendedMessagesByUser(UserT user) {
		return messagesDAO.findSendedMessagesByUser(user);
	}

	@Override
	public List<Message> findReceivedMessagesByUser(UserT user) {
		return messagesDAO.findReceivedMessagesByUser(user);
	}
	
	@Override
	public List<Message> findAllUserMessages(UserT user) {
		return messagesDAO.findAllUserMessages(user);
	}

	@Override
	public List<Message> findMessagesByPackage(PackageT packageT) {
		return messagesDAO.findMessagesByPackage(packageT);
	}

	@Override
	public int createMessage(Message message) {
		return messagesDAO.createMessage(message);
	}

	@Override
	public void deleteMessage(Message message) {
		messagesDAO.deleteMessage(message);
	}

	@Override
	public void deleteMessagesByPackage(PackageT packageT) {
		messagesDAO.deleteMessagesByPackage(packageT);		
	}

//	@Override
//	public Map<Integer, Set<Message>> getMessagesByPackages(List<Message> allMessages) {
//		Map<Integer, Set<Message>> map = new HashMap<>();
//		int idpackage = 0;
//		for (Message m : allMessages) {
//			idpackage = m.getPackageT().getIdPackage();
//			if (map.get(idpackage) == null) {
//				map.put(idpackage, m.getPackageT().getMessages());
//			}
//		}
//		return map;
//	}

}
