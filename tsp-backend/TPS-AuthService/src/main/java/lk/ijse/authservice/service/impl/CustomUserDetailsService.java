package lk.ijse.authservice.service.impl;


import lk.ijse.authservice.entity.User;
import lk.ijse.authservice.persistance.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 11/2/2023
 * Time : 5:19 PM
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> credential = userDao.findByUserName(username);
        return credential.map(User::new).orElseThrow(() -> new UsernameNotFoundException("user not found with userName :" + username));
    }
}
