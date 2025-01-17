package net.engineeringDigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    public void sendMail(){
        emailService.sendEmail("chidrawarsaurabh023@gmail.com","testing java ","email test");
    }
}




