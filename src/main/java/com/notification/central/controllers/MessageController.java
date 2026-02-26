package com.notification.central.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.notification.central.requests.MessageRequest;
import com.notification.central.responses.MessageResponse;
import com.notification.central.services.MessageService;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

	@Autowired
	MessageService messageService;
	
	@PostMapping(value = "/send")
	public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest dto) {
		return ResponseEntity.ok(messageService.sendMessage(dto));
	}
	
	@GetMapping(value = "/conversation/{senderId}/{recipientId}")
	public ResponseEntity<List<MessageResponse>> listMessagesByConversation(@PathVariable Long senderId, @PathVariable Long recipientId) {
		return ResponseEntity.ok(messageService.listConversationBetweenUsers(senderId, recipientId));
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
		messageService.deleteMessageById(id);
		return ResponseEntity.noContent().build();
	}
}
