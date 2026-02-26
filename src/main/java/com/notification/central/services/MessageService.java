package com.notification.central.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.central.entities.Message;
import com.notification.central.entities.User;
import com.notification.central.repositories.MessageRepository;
import com.notification.central.repositories.UserRepository;
import com.notification.central.requests.MessageRequest;
import com.notification.central.responses.MessageResponse;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;
	
	@Autowired 
	UserRepository userRepository;
	
	public MessageResponse sendMessage(MessageRequest request) {
		User sender = userRepository.findById(request.getSenderUserId())
	            .orElseThrow(() -> new RuntimeException("Sender not found"));

	    User recipient = userRepository.findById(request.getRecipientUserId())
	            .orElseThrow(() -> new RuntimeException("Recipient not found"));

	    Message message = new Message();
	    message.setSenderUser(sender);
	    message.setRecipientUser(recipient);
	    message.setContent(request.getContent());
	    message.setSendAt(LocalDateTime.now());
	    message.setRead(false);

	    messageRepository.save(message);

	    return new MessageResponse(message);
	}
	
	public List<MessageResponse> listConversationBetweenUsers(Long senderId, Long recipientId) {
		return messageRepository.findConversation(senderId, recipientId);
	}
	
	public void deleteMessageById(Long id) {
		if (messageRepository.existsById(id)) {
			messageRepository.deleteById(id);
		}
		else {
			throw new RuntimeException("This message not exists.");
		}
	}
}
