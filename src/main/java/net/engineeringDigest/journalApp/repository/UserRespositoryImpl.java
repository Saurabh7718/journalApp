package net.engineeringDigest.journalApp.repository;

import net.engineeringDigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;


import java.util.List;


@Component
public class UserRespositoryImpl {


    //mongotemplate provide abstraction to interact with database
    @Autowired
    private MongoTemplate mongoTemplate;


//    public List<net.engineeringDigest.journalApp.entity.User> getUserForSA() {
//        Query query = new Query();
////        query.addCriteria(Criteria.where("email").exists(true));
////        query.addCriteria(Criteria.where("email").ne(null).ne(""));
//
//        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
//        query.addCriteria(Criteria.where("sentiementAnalysis").is(true));
//        //here by default taking and operator because queries are different
//
//
//        // what if we want apply and, or condition on addCriteria then we need to create an object of Criteria
    /// /        Criteria criteria = new Criteria();
    /// /        query.addCriteria(criteria.orOperator(Criteria.where("email").exists(true),
    /// /                Criteria.where("sentiementAnalysis").is(true)));
    /// /
//
//            return  mongoTemplate.find(query, net.engineeringDigest.journalApp.entity.User.class);
//
//
//
//
//    }


    public List<User> getUserForSA() {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        return mongoTemplate.find(query, net.engineeringDigest.journalApp.entity.User.class);
    }

}
