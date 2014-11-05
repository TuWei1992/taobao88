package org.taobao88.taobao.enterprise.service;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.Goods;
import org.taobao88.taobao.enterprise.entity.UserT;

public interface MailService {

	public void sendSimpleMessage(String from, String to, String subject, String message);
	
	public void sendOrderMessage(String from, String to, String subject, UserT user, List<Goods> recomendations);
	
}
