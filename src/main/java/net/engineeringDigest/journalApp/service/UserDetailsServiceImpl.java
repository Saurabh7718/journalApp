package net.engineeringDigest.journalApp.service;

import net.engineeringDigest.journalApp.entity.User;
import net.engineeringDigest.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    //UserDetailsService implementation t fetch user details
    //UserDetails is interface provided by springboot for security handling fo  r user
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassWord())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
            return userDetails;

        }
        throw new UsernameNotFoundException("user is not found with username: " + username);
    }
}
