package com.example.autorent.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MailService.class})
@ActiveProfiles({"GULA-YEZ8-9WAK-7FCO"})
@ExtendWith(SpringExtension.class)
class MailServiceTest {
    @MockBean
    private JavaMailSender javaMailSender;

    @Autowired
    private MailService mailService;

    /**
     * Method under test: {@link MailService#sendEmail(String, String, String)}
     */
    @Test
    void testSendEmail() throws MailException {
        doNothing().when(javaMailSender).send((SimpleMailMessage) any());
        mailService.sendEmail("alice.liddell@example.org", "Hello from the Dreaming Spires", "Text");
        verify(javaMailSender).send((SimpleMailMessage) any());
    }

    /**
     * Method under test: {@link MailService#sendHtmlEmail(String, String, String)}
     */
    @Test
    void testSendHtmlEmail() throws MessagingException, MailException {
        doNothing().when(javaMailSender).send((MimeMessage) any());
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        mailService.sendHtmlEmail("alice.liddell@example.org", "Hello from the Dreaming Spires", "Text");
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send((MimeMessage) any());
    }


}

