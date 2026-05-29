package com.company.controller;

import com.company.bean.MailDetail;
import com.company.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     * Endpoint: Send a plain text email
     *
     * POST http://localhost:8080/api/send-mail
     *
     * Sample Request Body:
     * {
     *   "recipient": "someone@example.com",
     *   "subject": "Test Mail",
     *   "msgBody": "Hello from Ankush!"
     * }
     */
    @PostMapping("/send-mail")
    public String sendMail(@RequestBody MailDetail mailDetail) {
        return mailService.sendMail(mailDetail);
    }

    /**
     * Endpoint: Send an email with a file attachment
     *
     * POST http://localhost:8080/api/send-mail-attachment
     *
     * Sample Request Body:
     * {
     *   "recipient": "someone@example.com",
     *   "subject": "Test Mail with Attachment",
     *   "msgBody": "Please find the attached file.",
     *   "attachment": "C:/Users/Ankush/Documents/file.pdf"
     * }
     */
    @PostMapping("/send-mail-attachment")
    public String sendMailWithAttachment(@RequestBody MailDetail mailDetail) {
        return mailService.sendMailWithAttachment(mailDetail);
    }
}
