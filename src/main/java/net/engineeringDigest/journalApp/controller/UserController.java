package net.engineeringDigest.journalApp.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import net.engineeringDigest.journalApp.apiResponse.WeatherResponse;
import net.engineeringDigest.journalApp.entity.User;
import net.engineeringDigest.journalApp.repository.UserRepository;
import net.engineeringDigest.journalApp.service.UserService;
import net.engineeringDigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//controller class creates only requests(controllers) it does not have implementation of methods
// controller find methods in service class
// service class contains methods to interact with database
//service class is dependent on repository class which contains mongoRepository interface which gives
// different methods and implementation which are already prepared(boiler code)
//service class takes help from mongoRepository interface to get methods


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;


    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //SecurityContextHolder.getContext().getAuthentication(); holds username and password when session starts a
// after authentication
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassWord(user.getPassWord());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(userInDb, HttpStatus.OK);


    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("greet")
    public ResponseEntity<?> greeting() throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String greeting = "";

        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        if (weatherResponse != null && weatherResponse.getCurrent() != null) {
            greeting = ",weather feels like " + weatherResponse.getCurrent().getFeelslike();
            return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);

        }


        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.NOT_FOUND);

    }


}
