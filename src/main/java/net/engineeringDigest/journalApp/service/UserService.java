package net.engineeringDigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringDigest.journalApp.controller.JournalEntryControllerV2;
import net.engineeringDigest.journalApp.entity.User;
import net.engineeringDigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
//@Slf4j
public class UserService {


    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

   // private static final Logger logger= LoggerFactory.getLogger(UserService.class);


    public boolean saveNewUser(User user) {
        try {
            String EncodePass = passwordEncoder.encode(user.getPassWord());
            user.setPassWord(EncodePass);
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;


        } catch (Exception e) {
           // log .error("error occured for {}:",user.getUserName(),e);
            return false;
        }


    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User saveAdmin(User user) {
        String EncodePass = passwordEncoder.encode(user.getPassWord());
        user.setPassWord(EncodePass);
        user.setRoles(Arrays.asList("ADMIN"));
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }


}
