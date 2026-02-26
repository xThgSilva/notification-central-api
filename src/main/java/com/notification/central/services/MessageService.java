package com.notification.central.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.central.dto.MessageDTO;
import com.notification.central.entities.Message;
import com.notification.central.entities.User;
import com.notification.central.repositories.MessageRepository;
import com.notification.central.repositories.UserRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;
	
	@Autowired 
	UserRepository userRepository;
	
	public Message sendMessage(MessageDTO dto) {
		Message message = new Message();
		
		User sender = userRepository.findById(dto.getSenderUserId())
				.orElseThrow(() -> new RuntimeException("Sender user whith Id "+ dto.getSenderUserId() + " not found or not exists."));
		
		User recipient = userRepository.findById(dto.getRecipientUserId())
				.orElseThrow(() -> new RuntimeException("Recipient user with Id " + dto.getRecipientUserId() + " not found or not exists."));
		
		message.setSenderUser(sender);
		message.setRecipientUser(recipient);
		message.setContent(dto.getContent());
		message.setSendAt(LocalDateTime.now());
		message.setRead(false);
		
		return messageRepository.save(message);
	}
	
	public List<Message> listConversationBetweenUsers(Long senderId, Long recipientId) {
		return messageRepository.findConversation(senderId, recipientId);
	}
	
	public String deleteMessageById(Long id) {
		if (messageRepository.existsById(id)) {
			return "The message was deleted.";
		}
		else {
			throw new RuntimeException("This message not exists.");
		}
	}
}
