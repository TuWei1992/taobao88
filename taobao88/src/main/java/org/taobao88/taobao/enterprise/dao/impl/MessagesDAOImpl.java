package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.MessagesDAO;
import org.taobao88.taobao.enterprise.entity.Message;
import org.taobao88.taobao.enterprise.entity.PackageT;
import org.taobao88.taobao.enterprise.entity.UserT;

@Transactional
@Repository("messagesDAO")
public class MessagesDAOImpl implements MessagesDAO {

	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public Message findMessageById(int id) {
		return (Message) sessionFactory.getCurrentSession().createQuery("from Message where id = :id").setParameter("id", id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findSendedMessagesByUser(UserT user) {
		return (List<Message>) sessionFactory.getCurrentSession().createQuery("from Message where from_user = :from_user").setParameter("from_user", user.getIdUser()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findReceivedMessagesByUser(UserT user) {
		return (List<Message>) sessionFactory.getCurrentSession().createQuery("from Message where to_user = :to_user").setParameter("to_user", user.getIdUser()).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findAllUserMessages(UserT user) {
		return (List<Message>) sessionFactory.getCurrentSession().createQuery("from Message where to_user = :user_id or from_user = :user_id order by created_at").setParameter("user_id", user.getIdUser()).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findMessagesByPackage(PackageT packageT) {
		return (List<Message>) sessionFactory.getCurrentSession().createQuery("from Message where idpackage = :idpackage order by created_at").setParameter("idpackage", packageT.getIdPackage()).list();
	}

	@Override
	public int createMessage(Message message) {
		return (int) sessionFactory.getCurrentSession().save(message);
	}

	@Override
	public void deleteMessage(Message message) {
		sessionFactory.getCurrentSession().delete(message);
	}

	@Override
	public void deleteMessagesByPackage(PackageT packageT) {
		sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM messages WHERE idpackage = :idpackage").setParameter("idpackage", packageT.getIdPackage());
	}
}
