package org.taobao88.taobao.enterprise.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taobao88.taobao.enterprise.entity.Goods;
import org.taobao88.taobao.enterprise.entity.UserT;
import org.taobao88.taobao.enterprise.service.MailService;
import org.taobao88.taobao.mail.MailMail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Repository("mailService")
public class MailServiceImpl implements MailService {

	@Autowired private MailMail mailMail;
	
	@Override
	public void sendSimpleMessage(String from, String to, String subject, String message) {
		mailMail.sendMail(from, to, subject, message);		
	}

	@Override
	public void sendOrderMessage(String from, String to, String subject, UserT user, List<Goods> goods) {
		StringBuffer messageText = new StringBuffer();
		messageText.append("<h3>Order from " + user.getNameUser() + "</h3>");
		messageText.append("<h4>mail: " + user.getGmail() + "</h4>");
		messageText.append("<table rules=\"all\" style=\"width: 400px;\">");
		messageText.append("<thead>");
			messageText.append("<tr>");
				messageText.append("<th>Item Number</th>");
				messageText.append("<th>Href</th>");
				messageText.append("<th>Price</th>");
			messageText.append("</tr>");
		messageText.append("</thead>");
		messageText.append("<tbody>");
		for (Goods g : goods) {
			messageText.append("<tr>");
				messageText.append("<td>" + g.getIdGoods() + "</td>");
				messageText.append("<td>" + g.getHrefGoods() + "</td>");
				messageText.append("<td>" + g.getPriceGoods() + "</td>");
			messageText.append("</tr>");
		}
		messageText.append("</tbody>");
		messageText.append("</table>");		
		mailMail.sendMail(from, to, subject, messageText.toString());
	}

	@Override
	public void sendMessageByFreemarkerTemplate(Configuration cfg, Map<String, Object> templateModel, String from, String to, String subject, String templateName) {
		Template template = null;
		Writer out = new StringWriter();
		try {
			template = cfg.getTemplate(templateName);
			template.process(templateModel, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}
		sendSimpleMessage(from, to, subject, out.toString());
	}

}
