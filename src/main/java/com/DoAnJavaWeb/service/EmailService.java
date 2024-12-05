package com.DoAnJavaWeb.service;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final BlockingQueue<SimpleMailMessage> queue;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        this.queue = new LinkedBlockingQueue<>();
        this.start();
    }

    private void start() {
        Thread newThread = new Thread(() -> {
            while (true) {
                try {
                    SimpleMailMessage mail = queue.take();
                    mailSender.send(mail);
                    System.out.println("sent mail: " + mail);
                } catch (InterruptedException e) {
                    break;
                } catch (Throwable e) {
                    System.out.println("send mail error: " + e);
                }
            }
        });
        newThread.setName("mail-sender");
        newThread.start();
    }

    public void sendMail(String from, String to, String subject, String text) {
        if (from != null && to != null && subject != null && text != null) {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(from);
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(text);
            queue.add(mail);
        } else {
            System.out.println("send mail error: null value detected in email parameters.");
        }
    }

}
