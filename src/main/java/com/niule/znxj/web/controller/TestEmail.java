package com.niule.znxj.web.controller;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Created by administor on 2017/6/16.
 */
public class TestEmail {
    public static void main(String[] args) {
        try{
            String userName="1021490876@qq.com";
            String password="yyeuyscxttxybcbi";
            String smtp_server="smtp.qq.com";
            String from_mail_address=userName;
            String to_mail_address="1497665236@qq.com";

            Authenticator auth=new PopupAuthenticator(userName,password);
            Properties mailProps=new Properties();
            mailProps.put("mail.smtp.host", smtp_server);
            mailProps.put("mail.smtp.auth", "true");
            mailProps.put("username", userName);
            mailProps.put("password", password);

            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            mailProps.put("mail.smtp.ssl.enable", "true");
            mailProps.put("mail.smtp.ssl.socketFactory", sf);

            Session mailSession=Session.getDefaultInstance(mailProps, auth);
            mailSession.setDebug(true);
            MimeMessage message=new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(from_mail_address));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to_mail_address));
            message.setSubject("Mail Testw");

            MimeMultipart multi=new MimeMultipart();
            BodyPart textBodyPart=new MimeBodyPart();
            textBodyPart.setText("电子邮件测试内容w");
//textBodyPart.setFileName("37af4739a11fc9d6b311c712.jpg");

            multi.addBodyPart(textBodyPart);
            message.setContent(multi);
            message.saveChanges();
            Transport.send(message);
        }catch(Exception ex){
            System.err.println("邮件发送失败的原因是："+ex.getMessage());
            System.err.println("具体的错误原因");
            ex.printStackTrace(System.err);
        }
    }
}
class PopupAuthenticator extends Authenticator{
    private String username;
    private String password;
    public PopupAuthenticator(String username,String pwd){
        this.username=username;
        this.password=pwd;
    }
    public PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(this.username,this.password);
    }
}
