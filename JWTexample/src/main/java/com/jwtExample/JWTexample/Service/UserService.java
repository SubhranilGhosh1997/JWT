package com.jwtExample.JWTexample.Service;


import com.jwtExample.JWTexample.Repository.UserRepository;
import com.jwtExample.JWTexample.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserModel> getAllUsers() {
         return userRepository.findAll();
    }

    public UserModel getUserById(Long id) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public UserModel createUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserModel updateUser(Long id, UserModel user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load user from Db

        return userRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("User Not Found !!!"));
    }
}
