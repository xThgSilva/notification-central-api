package com.notification.central.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.central.entities.User;
import com.notification.central.repositories.UserRepository;
import com.notification.central.requests.UserRequest;
import com.notification.central.responses.UserResponse;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public UserResponse createAccount(UserRequest request) {
		User user = new User();
		
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPermission(request.getPermission());
		user.setPassword(request.getPassword());
		
		user = userRepository.save(user);
		
		return new UserResponse(user);
	}
	
	public List<UserResponse> listAllUsers(){
		return userRepository.findAll() .stream()
	            .map(UserResponse::new)
	            .toList();
	}
	
	public UserResponse findUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with Id " + id + " not found."));
		
		return new UserResponse(user);
	}
	
	public String deleteUserById(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return "User successfully deleted.";
		}
		else {
			throw new RuntimeException("This user does not exist.");
		}
	}
	
	public UserResponse updateUserById(Long id, UserRequest update) {
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
		
		return new UserResponse(userRepository.save(user));
	}
}