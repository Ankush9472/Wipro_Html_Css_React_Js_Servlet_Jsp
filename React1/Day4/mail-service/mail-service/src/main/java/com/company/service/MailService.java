package com.company.service;

import com.company.bean.MailDetail;

public interface MailService {

    /**
     * Sends a plain text email.
     *
     * @param mailDetail - contains recipient, subject, and message body
     * @return success or error message
     */
    String sendMail(MailDetail mailDetail);

    /**
     * Sends an email with a file attachment.
     *
     * @param mailDetail - contains recipient, subject, message body, and file path for attachment
     * @return success or error message
     */
    String sendMailWithAttachment(MailDetail mailDetail);
}
