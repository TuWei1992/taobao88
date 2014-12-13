package org.taobao88.taobao.enterprise.service;

import java.util.List;
import java.util.Map;

import org.taobao88.taobao.enterprise.entity.Goods;
import org.taobao88.taobao.enterprise.entity.UserT;

import freemarker.template.Configuration;

public interface MailService {

	public void sendSimpleMessage(String from, String to, String subject, String message);
	
	public void sendOrderMessage(String from, String to, String subject, UserT user, List<Goods> recomendations);
	
	public void sendMessageByFreemarkerTemplate(Configuration cfg, Map<String, Object> templateModel, String to, String subject, String templateName);
	
}
