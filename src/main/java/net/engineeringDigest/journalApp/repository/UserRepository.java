package net.engineeringDigest.journalApp.repository;

import net.engineeringDigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);

    void deleteByUserName(String name);



}
