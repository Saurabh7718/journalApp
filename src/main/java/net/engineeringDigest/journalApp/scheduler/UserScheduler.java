package net.engineeringDigest.journalApp.scheduler;

import net.engineeringDigest.journalApp.repository.UserRespositoryImpl;
import net.engineeringDigest.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRespositoryImpl userRespository;


    public void fetchUsersAndSendSAMail(){
        List<User> userForSA = userRespository.getUserForSA();


    }

}
