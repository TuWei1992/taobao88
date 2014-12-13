package org.taobao88.taobao.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by User on 28.05.14.
 */
public class MailMail {

    /*private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String from, String to, String subject, String msg) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
        /*MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper help = new MimeMessageHelper(message);

        help.setFrom(from);
        help.setTo(to);
        help.setSubject(subject);
        help.setText(msg, true);
        mailSender.send(message);*/
   // }

    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(final String from, final String to,final String subject,final String msg) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            message.setText(msg, "UFT-8");
            message.setSubject(subject, "UTF-8");
            message.setContent(msg, "UFT-8");
            
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
             helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(message.getSubject());
            helper.setText(msg, true);

            mailSender.send(message);
        }catch(MessagingException e){e.printStackTrace();}
    }
}
