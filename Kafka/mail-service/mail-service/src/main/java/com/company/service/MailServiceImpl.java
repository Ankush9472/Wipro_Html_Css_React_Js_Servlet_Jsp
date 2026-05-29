package com.company.service;

import com.company.bean.MailDetail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    // Reads sender email from application.properties (ankush8114@gmail.com)
    @Value("${spring.mail.username}")
    private String sender;

    /**
     * Sends a plain text email using SimpleMailMessage.
     * Use this for simple emails without attachments.
     */
    @Override
    public String sendMail(MailDetail mailDetail) {
        try {
            // Create a simple mail message
            SimpleMailMessage emailMessage = new SimpleMailMessage();

            // Set email fields
            emailMessage.setFrom(sender);
            emailMessage.setTo(mailDetail.getRecipient());
            emailMessage.setSubject(mailDetail.getSubject());
            emailMessage.setText(mailDetail.getMsgBody());

            // Send the email
            mailSender.send(emailMessage);

            return "Email sent successfully to: " + mailDetail.getRecipient();

        } catch (Exception e) {
            return "Error while sending email: " + e.getMessage();
        }
    }

    /**
     * Sends an email with a file attachment using MimeMessage.
     * The attachment path should be an absolute file path on the server.
     */
    @Override
    public String sendMailWithAttachment(MailDetail mailDetail) {

        // Create a MIME message (supports attachments)
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            // true = multipart message (required for attachments)
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            // Set email fields
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(mailDetail.getRecipient());
            mimeMessageHelper.setSubject(mailDetail.getSubject());
            mimeMessageHelper.setText(mailDetail.getMsgBody());

            // Attach the file from the given path
            FileSystemResource file = new FileSystemResource(new File(mailDetail.getAttachment()));

            if (!file.exists()) {
                return "Error: Attachment file not found at path -> " + mailDetail.getAttachment();
            }

            mimeMessageHelper.addAttachment(file.getFilename(), file);

            // Send the email
            mailSender.send(mimeMessage);

            return "Email with attachment sent successfully to: " + mailDetail.getRecipient();

        } catch (MessagingException e) {
            return "Error while sending email with attachment: " + e.getMessage();
        }
    }
}
