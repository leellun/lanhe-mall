package com.newland.lanhe.user.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class SendMailUtil {
    @Value("lanhe.mail.host")
    private String host;
    @Value("lanhe.mail.port")
    private String port;
    @Value("lanhe.mail.user")
    private String user;
    @Value("lanhe.mail.password")
    private String password;
    @Value("lanhe.mail.send-user")
    private String sendUser;
    @Value("lanhe.mail.transport.protocol")
    private String protocol;
    @Value("lanhe.mail.auth")
    private String auth;
    @Value("lanhe.mail.smtp.ssl.enable")
    private String sslEnable;
    /**
     * 创建工作队列，用于存放提交的等待执行任务
     */
    private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(20);

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,
            6,
            2,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(20),
            new ThreadPoolExecutor.AbortPolicy());

    public void sendMail(String receiveMail, String contentHtml) {
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.host", host);
            prop.setProperty("mail.transport.protocol", protocol);
            prop.setProperty("mail.smtp.auth", auth);
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", sslEnable);
            prop.put("mail.smtp.ssl.socketFactory", sf);

            //使用JavaMail发送邮件的5个步骤
            //1、创建session
            Session session = Session.getInstance(prop);
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            //2、通过session得到transport对象
            Transport ts = session.getTransport();
            //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
            ts.connect(host, Integer.parseInt(port), user, password);
            //4、创建邮件
            Message message = createMimeMessage(session, user, receiveMail, contentHtml);
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendSyncMail(String receiveMail, String contentHtml) {
        threadPoolExecutor.submit(() -> sendMail(receiveMail, contentHtml));
    }

    public MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String contentHtml) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, sendUser, "UTF-8"));
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));
        // 4. Subject: 邮件主题
        message.setSubject("UUToolKit网站账户电子邮件确认", "UTF-8");
        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(contentHtml, "text/html;charset=UTF-8"); //以本人网站为例
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }
}
