package net.engineeringDigest.journalApp.service;

import net.engineeringDigest.journalApp.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulersTest {

    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void testFetchusersAndSendEmail(){
        userScheduler.fetchUsersAndSendSAMail();
    }
}
