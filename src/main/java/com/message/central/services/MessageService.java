package com.message.central.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.message.central.entities.Message;
import com.message.central.entities.User;
import com.message.central.exceptions.NotFoundException;
import com.message.central.exceptions.SameSenderRecipientIdException;
import com.message.central.repositories.MessageRepository;
import com.message.central.repositories.UserRepository;
import com.message.central.requests.MessageRequest;
import com.message.central.responses.MessageResponse;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
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

	    messageRepository.save(message);
	    
	    MessageResponse response = new MessageResponse(message);

	    messagingTemplate.convertAndSend(
	        "/topic/messages/" + recipient.getId(),
	        response
	    );

	    messagingTemplate.convertAndSend(
	        "/topic/messages/" + sender.getId(),
	        response
	    );

	    return response;
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
