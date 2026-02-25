package com.notification.central.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.central.dto.UserDTO;
import com.notification.central.entities.User;
import com.notification.central.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User createAccount(UserDTO dto) {
		User user = new User();
		
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		
		user = userRepository.save(user);
		
		return user;
	}
	
	public List<User> listAllUsers(){
		return userRepository.findAll();
	}
	
	public User findUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with Id " + id + " not found."));
		
		return user;
	}
	
	public String deleteUserById(Long id) {
		userRepository.deleteById(id);
		
		if (!userRepository.existsById(id)) {
			return "User successfully deleted";
		}
		else {
			return "User not deleted";
		}
	}
	
	public User updateUserById(Long id, UserDTO update) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with Id " + id + " not found."));
		
		if(update.getName() != null && !update.getName().isBlank()) {
			user.setName(update.getName());
		}
		
		if(update.getEmail() != null && !update.getEmail().isBlank()) {
			user.setEmail(update.getEmail());
		}
		
		if(update.getPassword() != null && !update.getPassword().isBlank()) {
			user.setPassword(update.getPassword());
		}
		
		return userRepository.save(user);
	}
}





