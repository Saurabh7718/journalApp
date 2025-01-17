package net.engineeringDigest.journalApp.repository;

import net.engineeringDigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringDigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {


}
