package com.example.demo.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 邮件发送Bean
 *
 * @author zhuhuix
 * @date 2020-07-13
 */
@Service
@Component
public class MailManager {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);
    // 发件人
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 普通文本邮件发送
     *
     * @param to      收件人
     * @param subject 主题
     * @param text    内容
     */
    public void sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(this.from);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        try {
            this.javaMailSender.send(msg);
            logger.info(msg.toString());
        } catch (MailException ex) {
            logger.error(ex.getMessage());
        }

    }

    /**
     * HTML邮件发送
     *
     * @param to      收件人
     * @param subject 主题
     * @param text    内容
     */
    public void sendHTMLMail(String to, String subject, String text) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(msg, true);
            mimeMessageHelper.setFrom(this.from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, true);
            this.javaMailSender.send(msg);
            logger.info("to=" + to + "," + "subject=" + subject + "," + "text=" + text);
        } catch (MailException ex) {
            logger.error(ex.getMessage());
        } catch (MessagingException ex) {
            logger.error(ex.getMessage());
        }

    }

    /**
     * 发送带有附件的邮件
     * @param to 收件人
     * @param subject 主题
     * @param text 内容
     * @param filePath 附件
     */
    public void sendAttachmentMail(String to, String subject, String text, String filePath) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, true);
            FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
            String fileName = fileSystemResource.getFilename();
            mimeMessageHelper.addAttachment(fileName, fileSystemResource);
            javaMailSender.send(mimeMessage);
            logger.info("to=" + to + "," + "subject=" + subject + "," + "text=" + text);
        } catch (MailException ex) {
            logger.error(ex.getMessage());
        } catch (MessagingException ex) {
            logger.error(ex.getMessage());
        }
    }

}
