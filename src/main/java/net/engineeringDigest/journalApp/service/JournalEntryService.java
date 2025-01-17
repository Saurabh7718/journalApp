package net.engineeringDigest.journalApp.service;

import net.engineeringDigest.journalApp.entity.JournalEntry;
import net.engineeringDigest.journalApp.entity.User;
import net.engineeringDigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {


    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());

            //we are adding journalentry to the JournalEntry and User collection

            JournalEntry saved = journalEntryRepository.save(journalEntry);

            //we are adding to specific user in memory
            user.getJournalEntries().add(saved);

            //here adding explicitly adding illegal operation to check transaction is properly working or not
            // user.setUserName(null);
            //adding user to database by using userservice.saveEntry
            userService.saveUser(user);
        } catch (Exception e) {

             throw new RuntimeException("An error occured while saving entry", e);
        }

    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());

        //we are adding journalentry to the JournalEntry and User collection

        journalEntryRepository.save(journalEntry);


    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(String userName, ObjectId id) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            //in user checking journal entries and remove only if matches with provided id

            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));

            if (removed) {
                //then we are saving because new updation made on user
                userService.saveUser(user);

                //journal entry delete from journalEntries collection
                journalEntryRepository.deleteById(id);

            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured while deleteing entry...");
        }
        return removed;
    }


}
