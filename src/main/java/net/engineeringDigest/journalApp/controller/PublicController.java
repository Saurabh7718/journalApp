package net.engineeringDigest.journalApp.controller;


import net.engineeringDigest.journalApp.entity.User;
import net.engineeringDigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/public")
@RestController
public class PublicController {
    @Autowired
    private UserService userService;


    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userService.saveNewUser(user);

    }

    @GetMapping("/health-check")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
