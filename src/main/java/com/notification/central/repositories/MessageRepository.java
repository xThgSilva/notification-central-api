package com.notification.central.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.notification.central.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository <Message, Long>{

	// Query to find a conversation
	@Query("""
			   SELECT m FROM Message m
			   WHERE (m.senderUser.id = :user1 AND m.recipientUser.id = :user2)
			      OR (m.senderUser.id = :user2 AND m.recipientUser.id = :user1)
			   ORDER BY m.sendAt ASC
			""")
	List<Message> findConversation(Long user1, Long user2);
}
