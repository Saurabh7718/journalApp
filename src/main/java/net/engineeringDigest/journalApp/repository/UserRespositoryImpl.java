package net.engineeringDigest.journalApp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Queue;


@Component
public class UserRespositoryImpl {


    //mongotemplate provide abstraction to interact with database
    @Autowired
    private MongoTemplate mongoTemplate;


    public List<User> getUserForSA() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("email").exists(true));
//        query.addCriteria(Criteria.where("email").ne(null).ne(""));

        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        query.addCriteria(Criteria.where("sentiementAnalysis").is(true));
        //here by default taking and operator because queries are different


        // what if we want apply and, or condition on addCriteria then we need to create an object of Criteria
//        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.orOperator(Criteria.where("email").exists(true),
//                Criteria.where("sentiementAnalysis").is(true)));
//

        List<User> users = mongoTemplate.find(query, User.class);
        return users;

    }

}
