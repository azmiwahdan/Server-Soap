package com.example.soapServer.services;

import com.example.serverone.User;
import com.example.soapServer.respositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initData() {
        addUser(1, "Azmi", "Nokia1");
        addUser(2, "Jihad", "Nokia2");
        addUser(3, "Hani", "Nokia3");
        addUser(4, "Firnas", "Nokia4");
        addUser(5, "Mhmd", "Nokia5");

    }

    public User getUser(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseGet(() -> new User(0, "non-existing user", "non-device"));
    }

    public String addUser(int id, String name, String device) {
        Optional<User> testUser = userRepository.findById(id);
        if (testUser.isPresent()) return "User with id : " + id + " is already exists";
        User user = new User(id, name, device);
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
