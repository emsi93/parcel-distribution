package com.parcel.distribution.webapp.email.service.impl;

import com.parcel.distribution.db.entity.Link;
import com.parcel.distribution.db.repository.LinkRepository;
import com.parcel.distribution.utils.ErrorCode;
import com.parcel.distribution.webapp.email.email.Email;
import com.parcel.distribution.webapp.email.service.EmailService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Service
@Data
@ConfigurationProperties(prefix = "application")
@Transactional
public class EmailServiceImpl implements EmailService{
    @Value("${address.email}")
    private String userName;

    @Value("${password.email}")
    private String password;

    @Value("${mail.smtp.auth}")
    private String mailSmtpAuth;

    @Value("${mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnable;

    @Value("${mail.smtp.host}")
    private String mailSmtpHost;

    @Value("${mail.smtp.port}")
    private String mailSmtpPort;

    @Autowired
    private LinkRepository linkRepository;

    @Override
    public void sendEmail(Email email) {
        Properties properties = initProperties(mailSmtpAuth, mailSmtpStarttlsEnable, mailSmtpHost, mailSmtpPort);
        Session session = getSession(userName, password, properties);
        try {
            Message message = buildMessage(session, email);
            Transport.send(message);
            log.info("Sent message successfully to " + email);
            Link link = new Link(email.getUrl(), email.getRecipient(), LocalDateTime.now(), email.getType());
            saveLink(link);
        } catch (MessagingException e) {
            log.info(ErrorCode.generate() + " Message not sent to " + email);
            e.printStackTrace();
        }

    }

    private void saveLink(Link link) {
        Link link2 = Optional.ofNullable(linkRepository.findByEmailAndType(link.getEmail(), link.getType()))
                .orElse(new Link(null, null, null, null));
        if (link2.getEmail() != null)
            linkRepository.delete(link2);
        linkRepository.save(link);
    }

    private Message buildMessage(Session session, Email email) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userName));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getRecipient()));
        message.setSubject(email.getTopic());
        message.setText(email.getUrl());
        return message;
    }

    private Properties initProperties(String mailSmtpAuth, String mailSmtpStarttlsEnable, String mailSmtpHost, String mailSmtpPort) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", mailSmtpAuth);
        props.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
        props.put("mail.smtp.host", mailSmtpHost);
        props.put("mail.smtp.port", mailSmtpPort);
        return props;
    }

    private Session getSession(String userName, String password, Properties props) {
        return Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, password);
                    }
                });
    }


}
