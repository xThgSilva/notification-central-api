package com.notification.central.responses;

import java.time.LocalDateTime;

import com.notification.central.entities.Message;

public class MessageResponse {
	private Long id;
	private Long senderUserId;
	private Long recipientUserId;
	private String content;
	private LocalDateTime sendAt;
	private boolean isRead;
	
	public MessageResponse() {
	}

	public MessageResponse(Long id, Long senderUserId, Long recipientUserId, String content, LocalDateTime sendAt,
			boolean isRead) {
		this.id = id;
		this.senderUserId = senderUserId;
		this.recipientUserId = recipientUserId;
		this.content = content;
		this.sendAt = sendAt;
		this.isRead = isRead;
	}
	
	public MessageResponse(Message entity) {
	    this.id = entity.getId();
	    this.senderUserId = entity.getSenderUser().getId();
	    this.recipientUserId = entity.getRecipientUser().getId();
	    this.content = entity.getContent();
	    this.sendAt = entity.getSendAt();
	    this.isRead = entity.isRead();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(Long senderUserId) {
		this.senderUserId = senderUserId;
	}

	public Long getRecipientUserId() {
		return recipientUserId;
	}

	public void setRecipientUserId(Long recipientUserId) {
		this.recipientUserId = recipientUserId;
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