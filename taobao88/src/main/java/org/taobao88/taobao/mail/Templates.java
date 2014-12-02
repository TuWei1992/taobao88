package org.taobao88.taobao.mail;

import org.taobao88.taobao.enterprise.entity.BalanceOperation;
import org.taobao88.taobao.enterprise.entity.PackageT;
import org.taobao88.taobao.enterprise.entity.UserT;

public class Templates {

	public static String getPaymentCompletedTemplate(UserT user, PackageT packageT, BalanceOperation bo) {
		String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"ru\" lang=\"ru\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<title>Заголовок страницы</title>\n" +
                "</head><body><img src='http://cs5818.vk.me/g31878503/a_7e51a447.jpg'>" +
                "<p><h3>Payment from " + user.getFullNameUser() + " " + user.getFemailUser() + "</h3></p><table rules='all'>\n" +
                "        <tr><th>Package number</th>\n" +
                "                <td>" + packageT.getIdPackage() + "</td></tr>\n" +
                "        <tr><th>User</th>\n" +
                "                <td>" + user.getFullNameUser() + " " + user.getFemailUser() + " (" + user.getGmail() + ")" + "</td></tr>\n" +
                "        <tr><th>Package price</th>\n" +
                "                <td>" + packageT.getFullPrice() + "</td></tr>\n" +
                "        <tr><th>Payment date</th>\n" +
                "                <td>" + bo.getCreatedAt() + "</td></tr>\n" +
                "        <tr><th>Transaction number</th>\n" + 
                "				 <td>" + bo.getId() + "</td></tr>\n" + 
                "</table></body></html>";
		return html;
	}
	
}
