package com.avyukt.full.controller;

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

import com.avyukt.full.exception.UserNotFoundException;
import com.avyukt.full.model.User;
import com.avyukt.full.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/users")
	User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}
	
	@GetMapping("/users")
	List<User>getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{userid}")
	User getUserById(@PathVariable long userid) {
		return userRepository.findById(userid).orElseThrow(()->new UserNotFoundException(userid));
	}
	
	@PutMapping("/users/{userid}")
	User updateUser(@RequestBody User newuser, @PathVariable long userid) {
		return userRepository.findById(userid).map(user->{
			user.setName(newuser.getName());
			user.setUsername(newuser.getUsername());
			user.setEmail(newuser.getEmail());
			return userRepository.save(user);
		}).orElseThrow(()->new UserNotFoundException(userid));
	}
	
	@DeleteMapping("/users/{userid}")
	String deleteUser(@PathVariable long userid) {
		if(!userRepository.existsById(userid)) {
			throw new UserNotFoundException(userid);
		}
		userRepository.deleteById(userid);
		return "User With ID "+userid+" Has Been Deleted Successfully";
	}
	
}
