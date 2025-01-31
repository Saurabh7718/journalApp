package net.engineeringDigest.journalApp.scheduler;

import net.engineeringDigest.journalApp.entity.JournalEntry;
import net.engineeringDigest.journalApp.entity.User;
import net.engineeringDigest.journalApp.repository.UserRespositoryImpl;
import net.engineeringDigest.journalApp.service.EmailService;
import net.engineeringDigest.journalApp.service.SentimentalAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import net.engineeringDigest.journalApp.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRespositoryImpl userRespository;


    @Autowired
    private SentimentalAnalysisService sentimentalAnalysisService;


    @Scheduled(cron="0 * * ? * *")
    public void fetchUsersAndSendSAMail(){
        List<User> users=userRespository.getUserForSA();
        for(User user:users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> collect = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minusDays(7))).map(x -> x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ", collect);
            String sentiment = sentimentalAnalysisService.getSentiment(entry);
          //  emailService.sendEmail(user.getEmail(),"Sentiment for last 7 days",sentiment);

        }

     }

}
