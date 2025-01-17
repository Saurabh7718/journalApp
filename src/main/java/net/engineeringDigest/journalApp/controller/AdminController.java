package net.engineeringDigest.journalApp.controller;

import net.engineeringDigest.journalApp.cache.AppCache;
import net.engineeringDigest.journalApp.entity.User;
import net.engineeringDigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;


    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public ResponseEntity<?> createAdmin(@RequestBody User user) {
        User byUserName = userService.findByUserName(user.getUserName());
        if (!byUserName.getUserName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            User user1 = userService.saveAdmin(user);
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        }

    }

    
    @GetMapping("/clear-app-cache")
    public void clearAppCache() {
        appCache.init();
    }

}
