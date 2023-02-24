package com.zerobase.cms.user.client.service;

import com.zerobase.cms.user.client.MailgunClient;
import com.zerobase.cms.user.client.mailgun.SendMailForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailSendServiceTest {

    @Autowired
    private MailgunClient mailgunClient;

    @Test
    void EmailTest() {

        SendMailForm form = SendMailForm.builder()
            .to("sin99145@naver.com")
            .subject("test")
            .from("test@test.com")
            .text("test mail")
            .build();

        mailgunClient.sendEmail(form);
    }
}