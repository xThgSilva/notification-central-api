package com.notification.central.responses;

import com.notification.central.entities.PermissionType;
import com.notification.central.entities.User;

public class UserResponse {
	private Long id;
	private String name;
	private String email;
	private PermissionType permission;
	
	public UserResponse() {
	}

	public UserResponse(Long id, String name, String email, PermissionType permission) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.permission = permission;
	}

	public UserResponse(User entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.permission = entity.getPermission();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PermissionType getPermission() {
		return permission;
	}

	public void setPermission(PermissionType permission) {
		this.permission = permission;
	}
}