package com.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/")
  ResponseWrapper<List<User>> getAllUsers() {
    List<User> users = userRepository.findAll();
    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), users);
  }

  @GetMapping("/{id}")
  ResponseWrapper<User> getUser(@PathVariable String id) {
    User user = userRepository.findById(id).orElse(null);
    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), user);
  }

  @PostMapping("/")
  ResponseWrapper<User> createUser(@RequestBody User user) {
    User savedUser = userRepository.save(user);
    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), savedUser);
  }

  @PutMapping("/{id}")
  ResponseWrapper<User> updateUser(@PathVariable String id, @RequestBody User user) {
    User oldUser = userRepository.findById(id).orElse(null);
    if (oldUser != null) {
      oldUser.setName(user.getName());
      oldUser.setEmail(user.getEmail());
      oldUser.setPassword(user.getPassword());
      User updatedUser = userRepository.save(oldUser);
      return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), updatedUser);
    } else {
      return new ResponseWrapper<>(new ResponseWrapper.Status(404, "User Not Found"), null);
    }
  }

  @DeleteMapping("/{id}")
  ResponseWrapper<String> deleteUser(@PathVariable String id) {
    userRepository.deleteById(id);
    return new ResponseWrapper<>(new ResponseWrapper.Status(200, "Success"), id);
  }
}
