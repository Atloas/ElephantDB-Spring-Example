package com.agh.ElephantDBSpringExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    Iterable<User> getUsers() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PostMapping("/users")
    User postUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
