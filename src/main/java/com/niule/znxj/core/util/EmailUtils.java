package com.niule.znxj.core.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtils {

    private static final String USER = "kefu@ronglaoban.cn";
    private static final String PASSWORD = "Kefu6676";
    private static final String SMTP_ADDRESS = "smtp.exmail.qq.com";
    private static final String SMTP_PORT = "465";
    private static final String SEND_EMAIL_ADDRESS = "kefu@ronglaoban.cn";
    private static final String TITLE = "智能巡检";
    private static final String CHARSET = "utf-8";


//    public static void main(String[] args) {
//        String[] strings = new String[]{"13203927924@163.com","1497665236@qq.com"};
//        sendEmails(strings,"测试邮件发送","这是一个测试");
//    }


    public static boolean sendEmails(String USER ,String PASSWORD ,String SMTP_ADDRESS ,String SMTP_PORT,String[] emails, String title, String content){
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(SMTP_ADDRESS);
            email.setSslSmtpPort(SMTP_PORT);
            email.setAuthentication(USER, PASSWORD);
            email.setCharset(CHARSET);
            email.setSSL(true);
            email.addTo(emails);
            email.setFrom(USER, TITLE, CHARSET);
            email.setSubject(title);
            email.setHtmlMsg(content);
            email.send();
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean sendEmails(String USER ,String PASSWORD ,String SMTP_ADDRESS ,String SMTP_PORT,String emailAddress, String title, String content){
            HtmlEmail email = new HtmlEmail();
            email.setHostName(SMTP_ADDRESS);
            email.setSslSmtpPort(SMTP_PORT);
            email.setAuthentication(USER, PASSWORD);
            email.setCharset(CHARSET);
            email.setSSL(true);
        try {
            email.addTo(emailAddress);
            email.setFrom(USER, TITLE, CHARSET);
            email.setSubject(title);
            email.setHtmlMsg(content);
            email.send();
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendEmail(String USER ,String PASSWORD ,String SMTP_ADDRESS ,String SMTP_PORT,String emailNum, String title, String content){
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(SMTP_ADDRESS);
            email.setSslSmtpPort(SMTP_PORT);
            email.setAuthentication(USER, PASSWORD);
            email.setCharset(CHARSET);
            email.setSSL(true);
            email.addTo(emailNum);
            email.setFrom(USER, TITLE, CHARSET);
            email.setSubject(title);
            email.setHtmlMsg(content);
            email.send();
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void main(String[] args)throws AddressException, MessagingException {
        String[] strings = new String[]{"1021490876@qq.com","815906779@qq.com"};
        sendEmails("474711345@qq.com","tigwspgciwhjbgeg","smtp.qq.com","465",strings,"this is  a test","内容为： 这是第一封java发送来的邮件。");
//        Properties properties = new Properties();
//        properties.put("mail.transport.protocol", "smtp"); // 连接协议
//        properties.put("mail.smtp.host", "smtp.qq.com"); // 主机名
//        properties.put("mail.smtp.port", 465);  // 端口号
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.ssl.enable", "true");  // 设置是否使用ssl安全连接 ---一般都使用
//        properties.put("mail.debug", "true"); // 设置是否显示debug信息 true 会在控制台显示相关信息
//        // 得到回话对象
//        Session session = Session.getInstance(properties);
//        // 获取邮件对象
//        Message message = new MimeMessage(session);
//        // 设置发件人邮箱地址
//        message.setFrom(new InternetAddress("474711345@qq.com"));
//        // 设置收件人地址
//        message.setRecipients( MimeMessage.RecipientType.TO, new InternetAddress[] { new InternetAddress("815906779@qq.com") });
//        // 设置邮件标题
//        message.setSubject("由JavaMail发出的测试邮件");
//        // 设置邮件内容
//        message.setText("内容为： 这是第一封java发送来的邮件。");
//        // 得到邮差对象
//        Transport transport = session.getTransport();
//        // 连接自己的邮箱账户
//        transport.connect("474711345@qq.com", "tigwspgciwhjbgeg");// 密码为刚才得到的授权码
//        // 发送邮件
//        transport.sendMessage(message, message.getAllRecipients());

    }
}
