package org.taobao88.taobao.enterprise.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taobao88.taobao.enterprise.dao.MessagesDAO;
import org.taobao88.taobao.enterprise.entity.Message;
import org.taobao88.taobao.enterprise.entity.PackageT;
import org.taobao88.taobao.enterprise.entity.UserT;
import org.taobao88.taobao.enterprise.service.MessagesService;

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
		message.setCreatedAt(new Timestamp(new Date().getTime()));
		message.setUpdatedAt(new Timestamp(new Date().getTime()));
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

	@Override
	public int getUnreadedMessagesCount(int userId) {
		return messagesDAO.getUnreadedMessagesCount(userId);
	}

	@Override
	public void markMessagesAsReaded(int userId) {
		messagesDAO.markMessagesAsReaded(userId);		
	}
}
