package net.engineeringDigest.journalApp.scheduler;

import net.engineeringDigest.journalApp.entity.JournalEntry;
import net.engineeringDigest.journalApp.entity.User;
import net.engineeringDigest.journalApp.enums.Sentiment;
import net.engineeringDigest.journalApp.model.SentimentData;
import net.engineeringDigest.journalApp.repository.UserRespositoryImpl;
import net.engineeringDigest.journalApp.service.EmailService;
import net.engineeringDigest.journalApp.service.SentimentalAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import net.engineeringDigest.journalApp.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
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


    @Autowired
    private KafkaTemplate<String, SentimentData> kafkaTemplate;

    @Scheduled(cron = "0 * * ? * *")
    public void fetchUsersAndSendSAMail() {
        List<User> users = userRespository.getUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
//            String entry = String.join(" ", collect);
//            String sentiment = sentimentalAnalysisService.getSentiment(entry);
//          //  emailService.sendEmail(user.getEmail(),"Sentiment for last 7 days",sentiment);


            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();

            for (Sentiment sentiment : sentiments) {
                if (sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }

            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;

            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();

                }
            }

            if (mostFrequentSentiment != null) {
                SentimentData sentimentData  = SentimentData.builder().email(user.getEmail()).sentiment("Sentiment for last 7 days" + mostFrequentSentiment)    .build();
                kafkaTemplate.send("foods",sentimentData.getEmail(),sentimentData);
            }

        }

    }

}

