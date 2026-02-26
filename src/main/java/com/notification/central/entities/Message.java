package com.notification.central.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_message")
public class Message  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "sender_user_id")
	private User senderUser;
	
	@ManyToOne
	@JoinColumn(name = "recipient_user_id")
	private User recipientUser;
	private String content;
	private LocalDateTime sendAt;
	private boolean isRead;
	
	public Message() {
	}

	public Message(Long id, User senderUser, User recipientUser, String content, LocalDateTime sendAt, boolean isRead) {
		this.id = id;
		this.senderUser = senderUser;
		this.recipientUser = recipientUser;
		this.content = content;
		this.sendAt = sendAt;
		this.isRead = isRead;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSenderUser() {
		return senderUser;
	}

	public void setSenderUser(User senderUser) {
		this.senderUser = senderUser;
	}

	public User getRecipientUser() {
		return recipientUser;
	}

	public void setRecipientUser(User recipientUser) {
		this.recipientUser = recipientUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getSendAt() {
		return sendAt;
	}

	public void setSendAt(LocalDateTime sendAt) {
		this.sendAt = sendAt;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
}