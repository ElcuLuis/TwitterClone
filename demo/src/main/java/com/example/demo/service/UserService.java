package com.example.demo.service;

import com.example.demo.model.Follow;
import com.example.demo.model.User;
import com.example.demo.repository.FollowRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    private final FollowRepository followRepository;


    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, FollowRepository followRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    public User createUser(String name, String parola){

        if(!isValid(parola)){
            throw new RuntimeException("invalid password");
        }
        if(userRepository.findByUsername(name) != null){
            throw new RuntimeException("User already taken");
        }
        User user = new User();
        user.setUsername(name);
        user.setParola(bCryptPasswordEncoder.encode(parola));
        userRepository.save(user);
        return user;

    }
    public List<User> allUsers(){
        return userRepository.findAll();
    }
    public void deleteAll(){
        userRepository.deleteAll();
    }
    public boolean login(String name, String parola){
        User user = userRepository.findByUsername(name);
        if (user == null){
            throw new RuntimeException("User doesn't exist");
        }


        if (!bCryptPasswordEncoder.matches(parola, user.getParola())){
            throw new RuntimeException("Wrong password");
        }
        return true;
    }
    public void deleteUser(String username){
        User user = userRepository.findByUsername(username);
        userRepository.deleteById(user.getId());
    }
    public void follow(String userNameFollower, String userNameFollowed){
         User userFollower = userRepository.findByUsername(userNameFollower);
         if(userFollower == null){
             throw new RuntimeException("user follower is null");
         }
         User userFollowed = userRepository.findByUsername(userNameFollowed);
         if(userFollowed == null){
             throw new RuntimeException("user followed is null");
         }
         Follow follow = new Follow();
         userFollower.addFollower(follow);
         userFollowed.addFollowed(follow);
         followRepository.save(follow);
    }


    private final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public boolean isValid( String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
