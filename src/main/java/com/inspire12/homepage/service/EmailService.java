package com.inspire12.homepage.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor(staticName = "of")
public class EmailService {

    @Autowired
    public JavaMailSender javaMailSender;

    @Async
    public void sendMail(String email) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        String authorizedToken = createRandomText(10);
        // simpleMessage.setFrom("보낸사람@naver.com"); // NAVER, DAUM, NATE일 경우 넣어줘야 함
        simpleMessage.setTo(email);
        simpleMessage.setSubject("이메일 인증");
        simpleMessage.setText("인증번호: " + authorizedToken);
        javaMailSender.send(simpleMessage);
    }

    private String createRandomText(int length) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(ThreadLocalRandom.current().nextInt(10));
        }
        return text.toString();
    }
}
