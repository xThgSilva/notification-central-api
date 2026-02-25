package com.notification.central.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.notification.central.entities.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

}
