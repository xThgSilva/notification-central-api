package com.notification.central.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.central.entities.Message;
import com.notification.central.entities.User;
import com.notification.central.exceptions.NotFoundException;
import com.notification.central.exceptions.SameSenderRecipientIdException;
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
	            .orElseThrow(() -> new NotFoundException("Sender not found"));

	    User recipient = userRepository.findById(request.getRecipientUserId())
	            .orElseThrow(() -> new NotFoundException("Recipient not found"));

	    Message message = new Message();
	    if (request.getSenderUserId() == request.getRecipientUserId() || request.getRecipientUserId() == request.getSenderUserId()) {
	    	throw new SameSenderRecipientIdException("The sender's ID must be different from the recipient's ID.");
	    }
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
			throw new NotFoundException("This message not exists to delete.");
		}
	}
}
