package com.notification.central.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.central.entities.PermissionType;
import com.notification.central.entities.User;
import com.notification.central.exceptions.EmailAlreadyExistsException;
import com.notification.central.exceptions.InsufficienteCharactersException;
import com.notification.central.exceptions.InvalidPermissionTypeException;
import com.notification.central.exceptions.NotFoundException;
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
		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException("Email already registered.");
		}
		user.setEmail(request.getEmail());
		
		if (request.getPermission() != PermissionType.USER || request.getPermission() != PermissionType.ADMIN) {
			throw new InvalidPermissionTypeException("The user permission type is invalid.");
		}
		user.setPermission(request.getPermission());

		if (request.getPassword().length() < 5) {
			throw new InsufficienteCharactersException("The password must be at least 5 characters long.");
		}
		user.setPassword(request.getPassword());

		user = userRepository.save(user);

		return new UserResponse(user);
	}

	public List<UserResponse> listAllUsers() {
		return userRepository.findAll().stream().map(UserResponse::new).toList();
	}

	public UserResponse findUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User with Id " + id + " not found."));

		return new UserResponse(user);
	}

	public void deleteUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User with Id " + id + " not found to delete."));
		
		userRepository.deleteById(id);
	}

	public UserResponse updateUserById(Long id, UserRequest update) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User with Id " + id + " not found to update."));

		if (update.getName() != null && !update.getName().isBlank()) {
			user.setName(update.getName());
		}

		if (update.getEmail() != null && !update.getEmail().isBlank()) {
			if (userRepository.findByEmail(update.getEmail()).isPresent()) {
				throw new EmailAlreadyExistsException("Email already registered and cannot be used.");
			}
		}

		if (update.getPassword() != null && !update.getPassword().isBlank()) {
			if (update.getPassword().length() < 5) {
				throw new InsufficienteCharactersException("The password must be at least 5 characters long.");
			}
		}
		return new UserResponse(userRepository.save(user));
	}
}