package com.example.soapServer.services;

import com.example.serverone.User;
import com.example.soapServer.respositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) return optionalUser.get();
        return null;
    }

    public String addUser(int id, String name, String device) {
        Optional<User> testUser = userRepository.findById(id);
        if (testUser.isPresent()) return "User with id : " + id + " is already exists";
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setDevice(device);
        userRepository.save(user);
        return "User with id : " + id + " added successfully";
    }

    public String deleteUser(int id) {
        Optional<User> testUser = userRepository.findById(id);
        if (testUser.isEmpty()) return "User with id : " + id + " is not exists";
        userRepository.deleteById(id);
        return "User with id : " + id + " deleted successfully";
    }

    public User updateUser(int id, String name, String device) {
        Optional<User> testUser = userRepository.findById(id);
        if (testUser.isPresent()) {
            User user = testUser.get();
            user.setName(name);
            user.setDevice(device);
            userRepository.save(user);
            return user;
        }
        return null;
    }
}
