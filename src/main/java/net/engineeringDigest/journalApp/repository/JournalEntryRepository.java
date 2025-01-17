package net.engineeringDigest.journalApp.repository;

import net.engineeringDigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
    JournalEntry getJournalEntriesById(ObjectId id);

}
